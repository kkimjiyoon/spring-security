package com.nhnacademy.security.certificate.config;

import com.nhnacademy.security.certificate.auth.CustomLoginSuccessHandler;
import com.nhnacademy.security.certificate.auth.CustomLogoutSuccessHandler;
import com.nhnacademy.security.certificate.repository.ResidentRepository;
import com.nhnacademy.security.certificate.user.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/redirect-index").authenticated()
                .requestMatchers("/certificates/**").authenticated()
                .anyRequest().permitAll()
                .and();

        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("id")
                .passwordParameter("password")
                .successHandler(loginSuccessHandler(null));

        http.logout()
                .logoutUrl("/logout")
                .deleteCookies("SESSION")
                .logoutSuccessHandler(logoutSuccessHandler(null));

        http.csrf().disable();

        http.exceptionHandling()
                .accessDeniedPage("/error/403");


        return http.build();
    }

    @Bean
    public CustomUserDetailsService userDetailsService(ResidentRepository residentRepository) {
        return new CustomUserDetailsService(residentRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomLoginSuccessHandler loginSuccessHandler(RedisTemplate<String, Object> sessionRedisTemplate) {
        return new CustomLoginSuccessHandler(sessionRedisTemplate);
    }

    @Bean
    public CustomLogoutSuccessHandler logoutSuccessHandler(RedisTemplate<String, Object> sessionRedisTemplate) {
        return new CustomLogoutSuccessHandler(sessionRedisTemplate);
    }
}
