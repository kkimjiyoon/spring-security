package com.nhnacademy.security.certificate.config;

import com.nhnacademy.security.certificate.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final RedisTemplate<Object, Object> sessionRedisTemplate;

    public WebConfig(RedisTemplate<Object, Object> redisTemplate) {
        this.sessionRedisTemplate = redisTemplate;
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");

//        registry.addViewController("/admin/**").setViewName("admin");
//        registry.addViewController("/private-project/**").setViewName("private-project");
//        registry.addViewController("/public-project/**").setViewName("public-project");

        registry.addViewController("/error/403").setViewName("error403");
        registry.addViewController("/profile").setViewName("profile");

        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/logout").setViewName("logout");

        registry.addRedirectViewController("/redirect-index", "/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SessionInterceptor(sessionRedisTemplate));
    }

}
