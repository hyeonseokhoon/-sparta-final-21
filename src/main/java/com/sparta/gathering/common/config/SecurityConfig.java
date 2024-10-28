package com.sparta.gathering.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;
    private final Environment environment;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider, Environment environment) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.environment = environment;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> {
                    if (isDevProfile()) {                   // dev 프로파일일 경우 Swagger 접근 허용
                        auth.requestMatchers(
                                "/swagger-ui/**",         // Swagger UI 경로 허용
                                "/v3/api-docs/**",        // Swagger API 문서 경로 허용
                                "/swagger-ui.html"        // Swagger UI 메인 경로 허용
                        ).permitAll();
                    }
                    auth
                            .requestMatchers(
                                    "/api/auth/**",           // 인증 API 경로 허용 (로그인 등)
                                    "/api/users/signup",     // 회원가입 경로 허용
                                    "/oauth2/**"             // OAuth2 경로 허용 (보류)
                            ).permitAll()                      // 해당 경로들은 인증 없이 접근 가능
                            .anyRequest().authenticated();     // 나머지 경로는 인증 필요
                })
                // JWT 인증 필터 추가
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // JWT 필터를 빈으로 등록
    @Bean
    public JwtFilter jwtAuthenticationFilter() {
        return new JwtFilter(jwtTokenProvider);
    }

    // 현재 활성화된 프로파일이 dev인지 확인하는 메서드
    private boolean isDevProfile() {
        return Arrays.asList(environment.getActiveProfiles()).contains("dev");
    }

}