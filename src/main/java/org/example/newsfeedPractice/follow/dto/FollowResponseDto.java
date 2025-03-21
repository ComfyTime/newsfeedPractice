package org.example.newsfeedPractice.follow.dto;

import lombok.Getter;

@Getter
public class FollowResponseDto {

    private final Long followerId;
    private final Long followingId;

    public FollowResponseDto(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public static FollowResponseDto of(Long followerId, Long followingId) {
        return new FollowResponseDto(followerId, followingId);
    }
}