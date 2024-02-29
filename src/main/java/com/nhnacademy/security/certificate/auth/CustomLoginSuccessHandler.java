package com.nhnacademy.security.certificate.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final RedisTemplate<String, Object> sessionRedisTemplate;

    public CustomLoginSuccessHandler(RedisTemplate<String, Object> sessionRedisTemplate) {
        this.sessionRedisTemplate = sessionRedisTemplate;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String sessionId = UUID.randomUUID().toString();

        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200); // 3일

        response.addCookie(cookie);


        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        String username = userDetails.getUsername();
        String authority = authorities.get(0).getAuthority();

        sessionRedisTemplate.opsForHash().put(sessionId, "username", username);
        sessionRedisTemplate.opsForHash().put(sessionId, "authority", authority);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
