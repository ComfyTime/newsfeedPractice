package org.example.newsfeedPractice.member.dto;

import lombok.Getter;

@Getter
public class MemberSaveRequestDto {

    private String email;
    private String name;
    private String password;
}
