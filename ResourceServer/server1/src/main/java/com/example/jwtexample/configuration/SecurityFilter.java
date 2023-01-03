package com.example.jwtexample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityFilter {

    JwtFilter jwtFilter;

    @Autowired
    SecurityFilter(JwtFilter jwtFilter)
    {
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
    throws Exception {

        httpSecurity.authorizeHttpRequests(auth -> {
            auth.anyRequest().hasAuthority("ROLE_USER");
        }).addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
