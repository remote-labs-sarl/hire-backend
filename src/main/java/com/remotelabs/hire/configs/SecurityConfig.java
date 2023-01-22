package com.remotelabs.hire.configs;

import com.remotelabs.hire.utils.AccountAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final AccountAuthenticationProvider accountAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {

        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(accountAuthenticationProvider);

        httpSecurity.csrf().disable()
                .authorizeHttpRequests()

                .requestMatchers(HttpMethod.GET, "/technologies").permitAll()
                .requestMatchers(HttpMethod.GET, "/countries").permitAll()
                .requestMatchers(HttpMethod.GET, "/candidates/filter").permitAll()
                .requestMatchers(req -> req.getRequestURI().contains("swagger-ui")).permitAll()
                .requestMatchers(req -> req.getRequestURI().contains("api-docs")).permitAll()
                .requestMatchers(HttpMethod.GET, "/webjars/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .requestMatchers(HttpMethod.GET, "/csrf").permitAll()
                .requestMatchers(HttpMethod.GET, "/").permitAll()

                .requestMatchers("/**").authenticated()
                .anyRequest()
                .hasAnyRole("ADMIN", "USER", "RECRUITER")
                .and()
                .httpBasic(withDefaults())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return httpSecurity.build();

    }
}
