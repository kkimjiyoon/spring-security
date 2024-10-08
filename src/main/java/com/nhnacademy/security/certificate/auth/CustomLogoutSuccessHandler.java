package com.nhnacademy.security.certificate.auth;

import com.nhnacademy.security.certificate.util.CookieUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final RedisTemplate<Object, Object> sessionRedisTemplate;

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public CustomLogoutSuccessHandler(RedisTemplate<Object, Object> redisTemplate) {
        this.sessionRedisTemplate = redisTemplate;
    }


    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String sessionId = CookieUtils.getCookieValue(request, "SESSION");
        if (Objects.nonNull(sessionId)) {
            sessionRedisTemplate.opsForHash().delete(sessionId, "username", "authority");
        }

        redirectStrategy.sendRedirect(request, response, "/");
    }
}
