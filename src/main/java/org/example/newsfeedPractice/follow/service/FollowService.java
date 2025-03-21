package org.example.newsfeedPractice.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.follow.entity.Follow;
import org.example.newsfeedPractice.follow.repository.FollowRepository;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void follow(Long followerId, Long followeeId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new IllegalStateException("팔로워 없음"));
        Member followee = memberRepository.findById(followeeId)
                .orElseThrow(() -> new IllegalStateException("팔로우 대상 없음"));

        if (followRepository.existsByFollowerIdAndFollowingId(followerId, followeeId)) {
            throw new IllegalStateException("이미 팔로우");
        }

        Follow follow = new Follow(follower, followee);
        followRepository.save(follow);
    }

    @Transactional
    public void unfollow(Long followerId, Long followeeId) {
        Member follower = memberRepository.findById(followerId)
                .orElseThrow(() -> new IllegalStateException("팔로워 없음"));
        Member followee = memberRepository.findById(followeeId)
                .orElseThrow(() -> new IllegalStateException("팔로우 대상 없음"));

        Follow follow = followRepository.findByFollowerIdAndFollowingId(followerId, followeeId)
                .orElseThrow(() -> new IllegalStateException("팔로우 하지 않음."));

        followRepository.delete(follow);
    }
}
