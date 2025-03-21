package org.example.newsfeedPractice.member.dto;

import lombok.Getter;

@Getter
public class MemberUpdatePasswordResponseDto {

    private final Long id;
    private final String password;

    public MemberUpdatePasswordResponseDto(Long id, String password) {
        this.id = id;
        this.password = password;
    }
}
