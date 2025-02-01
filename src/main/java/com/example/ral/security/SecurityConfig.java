package com.example.ral.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disabling CSRF for testing, enable in production
            .authorizeRequests()
                .requestMatchers("/api/register", "/api/login", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**")
                .permitAll()  // Allow access to Swagger UI and API documentation
                .anyRequest().authenticated()  // Require authentication for other requests
            .and()
            .formLogin()
                .permitAll()  // Enable form-based login
            .and()
            .logout().permitAll();  // Enable logout functionality

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Define BCryptPasswordEncoder for password hashing
    }
}
