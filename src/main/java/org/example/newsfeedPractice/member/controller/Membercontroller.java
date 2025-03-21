package org.example.newsfeedPractice.member.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.common.consts.Const;
import org.example.newsfeedPractice.member.dto.*;
import org.example.newsfeedPractice.member.service.MemberService;
import org.example.newsfeedPractice.post.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Membercontroller {

    private final MemberService memberService;
    private final PostService postService;

    @GetMapping("/profiles/{memberId}")
    public ResponseEntity<MemberResponseDto> getMemberProfile(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId
    ) {
        return ResponseEntity.ok(memberService.findMemberProfile(memberId));
    }

    @PutMapping("/profiles/{memberId}")
    public void memberProfileUpdate(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdateRequestDto memberUpdateRequestDto
    ) {
        memberService.updateMemberProfile(memberId, memberUpdateRequestDto);
    }

    @PutMapping("/password/{memberId}")
    public void updateMemberPassword(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @RequestBody MemberUpdatePasswordRequestDto memberUpdatePasswordRequestDto
    ) {
        memberService.updateMemberPassword(memberId, memberUpdatePasswordRequestDto);
    }
}
