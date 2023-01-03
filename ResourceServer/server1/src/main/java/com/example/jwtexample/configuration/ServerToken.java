package com.example.jwtexample.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import net.minidev.json.JSONObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.multipart.support.RequestPartServletServerHttpRequest;

import java.sql.Date;
import java.time.Instant;

@Configuration

public class ServerToken {
    String serverBearer;

    public ServerToken(){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("grant_type", "client_credentials");
        jsonObject.put("client_id", "resource-server1");
        jsonObject.put("client_secret", "secret1");

        HttpEntity<String> request = new HttpEntity<>(jsonObject.toString(), header);

        serverBearer = "Bearer " + restTemplate.postForObject("http://localhost:8111/oauth2/token", request, String.class);
        System.out.println(Date.from(Instant.now()) + " INFO: ACQUIRED SERVER TOKEN");
        System.out.println(Date.from(Instant.now()) + " INFO: SERVER TOKEN: " + serverBearer);
    }


}
