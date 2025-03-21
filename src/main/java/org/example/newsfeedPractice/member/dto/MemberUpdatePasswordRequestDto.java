package org.example.newsfeedPractice.member.dto;

import lombok.Getter;

@Getter
public class MemberUpdatePasswordRequestDto {

    private String oldPassword;
    private String newPassword;

    public MemberUpdatePasswordRequestDto(String oldPassword, String newPassword) {
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
