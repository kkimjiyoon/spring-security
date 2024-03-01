package com.nhnacademy.security.certificate.controller;

import com.nhnacademy.security.certificate.domain.AccessToken;
import com.nhnacademy.security.certificate.domain.GithubProfile;
import com.nhnacademy.security.certificate.domain.Resident;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.user.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OAuthController {

    private final ResidentRepository residentRepository;

    private final CustomUserDetailsService customUserDetailsService;

    private final RedisTemplate<String, Object> sessionRedisTemplate;

    private final String REDIRECT_URL = "http://localhost:8080/login/oauth2/code/github";
    private final String ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";

    private final String PROFILE_REQUEST_URL = "https://api.github.com/user";

    @Value("${client.id}")
    private String clientId;

    @Value("${client.secret}")
    private String clientSecret;


    @GetMapping("/login/oauth2/code/github")
    public String githubLogin(@RequestParam String code) {
        log.info("code: {}", code);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AccessToken> accessTokenResponse = restTemplate.exchange(ACCESS_TOKEN_URL,
                HttpMethod.POST,
                getAccessToken(code),
                AccessToken.class
        );

        String accessToken = Objects.requireNonNull(accessTokenResponse.getBody()).getAccessToken();

        log.info("access_token: {}", accessToken);

        return "redirect:/githubLogin/success?access_token=" + accessToken;
    }


    private HttpEntity<MultiValueMap<String, String>> getAccessToken(String code) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("client_id", clientId);
        params.add("client_secret", clientSecret);
        params.add("code", code);

        HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(params, headers);
    }

    @GetMapping("/githubLogin/success")
    public String githubLoginSuccess(@RequestParam("access_token") String accessToken, HttpServletResponse response, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<GithubProfile> profileResponse = restTemplate.exchange(PROFILE_REQUEST_URL,
                HttpMethod.GET,
                getUserInfo(accessToken),
                GithubProfile.class);

        GithubProfile githubProfile = profileResponse.getBody();

        Resident resident = residentRepository.findByResidentEmail(githubProfile.getEmail());


        String sessionId = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200); // 3일
        cookie.setPath("/");


        UserDetails userDetails = customUserDetailsService.loadUserByUsername(resident.getResidentId());
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        String username = userDetails.getUsername();
        String authority = authorities.get(0).getAuthority();

        sessionRedisTemplate.opsForHash().put(sessionId, "username", username);
        sessionRedisTemplate.opsForHash().put(sessionId, "authority", authority);

        log.info("githubProfile: {}", githubProfile);
        log.info("sessionId: {}", sessionId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.addCookie(cookie);

        return "redirect:/";
    }

    private HttpEntity<MultiValueMap<String, String>> getUserInfo(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "token " + accessToken);

        return new HttpEntity<>(headers);
    }

}
