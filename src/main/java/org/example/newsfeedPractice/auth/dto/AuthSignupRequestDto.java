package org.example.newsfeedPractice.auth.dto;

import lombok.Getter;

@Getter
public class AuthSignupRequestDto {

    private String email;
    private String name;
    private String password;
}
