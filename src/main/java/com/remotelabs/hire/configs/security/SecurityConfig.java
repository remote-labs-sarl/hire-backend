package com.remotelabs.hire.configs.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .cors().configurationSource(request -> {
                    CorsConfiguration corsConfig = new CorsConfiguration();
                    corsConfig.setAllowedOrigins(List.of("*"));
                    corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
                    corsConfig.setAllowedHeaders(List.of("*"));
                    corsConfig.setExposedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
                    corsConfig.setMaxAge(Duration.ofHours(1));
                    return corsConfig;
                }).and().csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/management/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/technologies").permitAll()
                .requestMatchers(HttpMethod.GET, "/countries").permitAll()
                .requestMatchers(HttpMethod.GET, "/candidates/filter").permitAll()
                .requestMatchers(req -> req.getRequestURI().contains("swagger-ui")).permitAll()
                .requestMatchers(req -> req.getRequestURI().contains("api-docs")).permitAll()
                .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .requestMatchers(HttpMethod.GET, "/csrf").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);
        return httpSecurity.build();

    }

}
