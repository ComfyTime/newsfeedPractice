package org.example.newsfeedPractice.member.dto;

import lombok.Getter;

@Getter
public class MemberUpdateResponseDto {

    private final Long id;
    private final String email;
    private final String name;

    public MemberUpdateResponseDto(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
