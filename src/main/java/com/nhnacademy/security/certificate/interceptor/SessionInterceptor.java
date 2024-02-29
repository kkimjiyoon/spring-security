package com.nhnacademy.security.certificate.interceptor;

import com.nhnacademy.security.certificate.util.CookieUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class SessionInterceptor implements HandlerInterceptor {
    private final RedisTemplate<Object, Object> sessionRedisTemplate;

    public SessionInterceptor(RedisTemplate<Object, Object> redisTemplate) {
        this.sessionRedisTemplate = redisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = CookieUtils.getCookieValue(request, "SESSION");
        if (Objects.nonNull(sessionId)) {
            String username = (String) sessionRedisTemplate.opsForHash().get(sessionId, "username");
            String authority = (String) sessionRedisTemplate.opsForHash().get(sessionId, "authority");

            request.setAttribute("username", username);
            request.setAttribute("authority", authority);
        }

        return true;
    }
}
