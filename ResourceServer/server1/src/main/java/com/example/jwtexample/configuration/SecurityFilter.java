package com.example.jwtexample.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
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
        httpSecurity.authorizeHttpRequests().anyRequest().permitAll().and().httpBasic().disable()
        .addFilterBefore(new JwtFilter(), BearerTokenAuthenticationFilter.class);

        return httpSecurity.build();
    }


}
