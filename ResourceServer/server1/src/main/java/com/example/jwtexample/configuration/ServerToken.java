package com.example.jwtexample.configuration;

import com.example.jwtexample.configuration.Model.Token;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;
import java.time.Instant;

@Configuration
public class ServerToken {
    String serverBearer;

    public ServerToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        String bodyParams = "grant_type=client_credentials&client_id=resource-server1&client_secret=secret1";
        header.setContentType(MediaType.APPLICATION_FORM_URLENCODED);


        HttpEntity<String> request = new HttpEntity<>(bodyParams, header);
        String url = "http://localhost:8111/oauth2/token";
        String response = restTemplate.postForObject(url, request, String.class);
        Token token = objectMapper().readValue(response, Token.class);
        serverBearer = "Bearer " + token.getAccessToken();

        System.out.println(Date.from(Instant.now()) + " INFO: ACQUIRED SERVER TOKEN");
        System.out.println(Date.from(Instant.now()) + " INFO: SERVER TOKEN: " + serverBearer);
    }


    ObjectMapper objectMapper()
    {
        JavaTimeModule jtm = new JavaTimeModule();
        return new ObjectMapper().registerModule(jtm);
    }

}
