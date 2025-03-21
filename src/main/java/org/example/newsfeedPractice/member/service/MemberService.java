package org.example.newsfeedPractice.member.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.member.dto.*;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberProfile(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member not found"));
        return new MemberResponseDto(
                member.getId(),
                member.getEmail(),
                member.getName()
        );
    }

    @Transactional
    public void updateMemberProfile(Long memberId, MemberUpdateRequestDto memberUpdateRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member not found"));
        member.updateProfile(
                memberUpdateRequestDto.getName(),
                memberUpdateRequestDto.getEmail()
        );
    }

    @Transactional
    public void updateMemberPassword(Long memberId, MemberUpdatePasswordRequestDto memberUpdatePasswordRequestDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member not found"));

        if (!member.getPassword().equals(memberUpdatePasswordRequestDto.getOldPassword())) {
            throw new IllegalStateException("Old password is incorrect");
        }

        if (member.getPassword().equals(memberUpdatePasswordRequestDto.getNewPassword())) {
            throw new IllegalStateException("New password cannot be the same as existing password.");
        }

        member.updatePassword(memberUpdatePasswordRequestDto.getNewPassword());
    }
}
