package org.example.newsfeedPractice.follow.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.common.consts.Const;
import org.example.newsfeedPractice.follow.dto.FollowResponseDto;
import org.example.newsfeedPractice.follow.service.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow/{followingId}")
    public ResponseEntity<FollowResponseDto> follow(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long followingId
    ) {
        followService.follow(memberId, followingId);

        FollowResponseDto responseDto = FollowResponseDto.of(
                memberId,
                followingId
        );

        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/follow/{followingId}")
    public ResponseEntity<FollowResponseDto> unfollow(
            @SessionAttribute(name = Const.LOGIN_MEMBER) Long memberId,
            @PathVariable Long followingId
    ) {
        followService.unfollow(memberId, followingId);

        FollowResponseDto responseDto = FollowResponseDto.of(
                memberId,
                followingId
        );

        return ResponseEntity.ok(responseDto);
    }

}