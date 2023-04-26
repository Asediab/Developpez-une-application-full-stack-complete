package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String lastName;
    private String mail;


    public JwtResponse(String accessToken, String lastName, String mail) {
        this.token = accessToken;
        this.lastName = lastName;
        this.mail = mail;
    }
}
