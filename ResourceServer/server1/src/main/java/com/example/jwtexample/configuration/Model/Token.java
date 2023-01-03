package com.example.jwtexample.configuration.Model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;


@Data
public class Token {

    @Getter(AccessLevel.NONE)
    String access_token;

    @Getter(AccessLevel.NONE)
    String token_type;

    Long expires_in;

    public String getAccessToken() { return access_token; }

    public String getTokenType() { return token_type; }

}
