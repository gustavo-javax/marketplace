package com.Marketplace.Marketplace.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // desabilita CSRF para Postman
                .authorizeHttpRequests()
                .anyRequest().permitAll(); // libera todas as rotas
        return http.build();
    }
}
