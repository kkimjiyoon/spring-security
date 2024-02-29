package com.nhnacademy.security.certificate.interceptor;

import com.nhnacademy.security.certificate.util.CookieUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class SessionInterceptor implements HandlerInterceptor {
    private final RedisTemplate<String, Object> sessionRedisTemplate;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String sessionId = CookieUtils.getCookieValue(request, "SESSION");
        log.info("{}",sessionId);
        if (Objects.nonNull(sessionId)) {
            String username = (String) sessionRedisTemplate.opsForHash().get(sessionId, "username");
            String authority = (String) sessionRedisTemplate.opsForHash().get(sessionId, "authority");

            request.setAttribute("username", username);
            request.setAttribute("authority", authority);

            log.info("{}", username);
            log.info("{}", authority);

        }

        return true;
    }
}
