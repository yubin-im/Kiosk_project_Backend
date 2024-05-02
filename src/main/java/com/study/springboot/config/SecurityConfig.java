package com.study.springboot.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtUtil jwtConfig;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(auth -> auth.disable());


        http
               .cors(corsConfig -> corsConfig.configurationSource(corsConfigurationSource()));

                http     //HTTP요청에 대한 보안설정을 시작한다.
                .authorizeHttpRequests( (auth) -> auth
                //루트 밑의 모든 경로에 대한 모든 요청을 허가한다.
                .requestMatchers( new AntPathRequestMatcher("/**") )
                .permitAll());
        http.sessionManagement(sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(new JwtAuthenticationFilter(jwtConfig), UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }


    //CORS 설정
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedHeaders(Collections.singletonList("*")); // 허용할 HTTP header
        config.setAllowedMethods(Collections.singletonList("*")); // 허용할 HTTP method
        config.setAllowedOriginPatterns(Collections.singletonList("*")); // 허용할 출처
        //config.setAllowedOriginPatterns(Collections.singletonList("http://127.0.0.1:8080")); // 허용할 출처
        config.setAllowCredentials(true); // 쿠키 인증 요청 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
