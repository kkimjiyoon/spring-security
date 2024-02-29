package com.nhnacademy.security.certificate.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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

    private final RedisTemplate<Object, Object> sessionRedisTemplate;

    public CustomLoginSuccessHandler(RedisTemplate<Object, Object> redisTemplate) {
        this.sessionRedisTemplate = redisTemplate;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String sessionId = UUID.randomUUID().toString();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<GrantedAuthority> authorities = new ArrayList<>(userDetails.getAuthorities());

        sessionRedisTemplate.opsForHash().put(sessionId, "username", userDetails.getUsername());
        sessionRedisTemplate.opsForHash().put(sessionId, "authority", authorities.get(0).getAuthority());

        log.info("{}", userDetails.getUsername());
        log.info("{}", authorities.get(0).getAuthority());


        Cookie cookie = new Cookie("SESSION", sessionId);
        cookie.setMaxAge(259200); // 3일

        response.addCookie(cookie);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}
