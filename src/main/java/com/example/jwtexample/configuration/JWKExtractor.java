package com.example.jwtexample.configuration;

import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class JWKExtractor {

    RestTemplate restTemplate;

    static JWKSet jwkSet;

    @Autowired
    public JWKExtractor()
    {
        this.restTemplate = new RestTemplate();
    }

    @Bean
    JWKSet jwkSet()
    {
       jwkSet = restTemplate.getForObject("http://localhost:8111", JWKSet.class);
       return jwkSet;
    }




}
