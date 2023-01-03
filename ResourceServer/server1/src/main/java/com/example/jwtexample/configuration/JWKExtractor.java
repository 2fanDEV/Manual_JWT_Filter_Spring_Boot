package com.example.jwtexample.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nimbusds.jose.jwk.JWKSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;

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
    throws ParseException {
        jwkSet = JWKSet.parse(restTemplate.getForObject("http://localhost:8111/oauth2/jwks", String.class));
        return jwkSet;
    }

    ObjectMapper objectMapper()
    {
        JavaTimeModule jtm = new JavaTimeModule();
        return new ObjectMapper().registerModule(jtm);
    }

}


