/*
 * (c) 2024 EPAM systems, Inc. All Rights Reserved. All information contained herein is, and remains the
 * property of EPAM Systems, Inc. and/or its suppliers and is protected by international intellectual
 * property law. Dissemination of this information or reproduction of this material is strictly forbidden,
 * unless prior written permission is obtained from EPAM Systems, Inc
 */

package com.epam.authtaskapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final String[] ENDPOINTS_WHITELIST = {"/actuator/**",
        "/index",
        "/login"
    };

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
            .authorizeRequests(request ->
                request.antMatchers(HttpMethod.GET, ENDPOINTS_WHITELIST).permitAll()
                    .antMatchers(HttpMethod.GET, "/blocked-users").hasAuthority("VIEW_ADMIN")
                    .antMatchers(HttpMethod.GET, "/info").hasAuthority("VIEW_INFO")
                    .anyRequest().authenticated())
            .formLogin()
            .loginPage("/login")
            .successForwardUrl("/index")
            .defaultSuccessUrl("/index")
            .and()
            .logout()
            .logoutUrl("/logout")
            .and()
            .csrf().disable()
            .build();
    }
}