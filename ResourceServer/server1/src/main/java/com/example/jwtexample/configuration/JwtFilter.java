package com.example.jwtexample.configuration;


import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;

import com.nimbusds.jwt.SignedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;


public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String jwtToken = request.getHeader("Authorization").replace("Bearer", "");
        SignedJWT extractedJwt;
        boolean couldVerify = false;

        try {
            extractedJwt = SignedJWT.parse(jwtToken);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        JWSVerifier jwsVerifier = null;
        try {
            jwsVerifier = new RSASSAVerifier(JWKExtractor.jwkSet.toPublicJWKSet().getKeys().get(0).toRSAKey());
        } catch  (JOSEException ex) {
            throw new RuntimeException(ex);
        }
        if(jwsVerifier == null)
                return;
        try {
           couldVerify =  extractedJwt.verify(jwsVerifier);
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
        if(!couldVerify)
        {
            return;
        }

        filterChain.doFilter(request, response);
    }
}
