package org.example.newsfeedPractice.auth.dto;

import lombok.Getter;

@Getter
public class AuthSignupResponseDto {

    private final Long id;
    private final String email;
    private final String name;
    private final String password;

    public AuthSignupResponseDto(Long id, String email, String name, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
