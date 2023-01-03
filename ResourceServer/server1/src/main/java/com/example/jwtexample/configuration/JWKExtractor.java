package com.example.jwtexample.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
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
        String result = restTemplate.getForObject("http://localhost:8111/oauth2/jwks", String.class);
       System.out.println(result);
       return jwkSet;
    }



}
