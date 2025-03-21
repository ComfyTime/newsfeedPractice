package org.example.newsfeedPractice.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.auth.dto.AuthLoginRequestDto;
import org.example.newsfeedPractice.auth.dto.AuthLoginResponseDto;
import org.example.newsfeedPractice.auth.dto.AuthSignupRequestDto;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        Member member = new Member(authSignupRequestDto.getEmail(), authSignupRequestDto.getName(), authSignupRequestDto.getPassword());
        memberRepository.save(member);
    }

    @Transactional
    public AuthLoginResponseDto login(AuthLoginRequestDto authLoginRequestDto) {

        Member member = memberRepository.findByEmail(authLoginRequestDto.getEmail())
                .orElseThrow(() -> new IllegalStateException("해당 ID 없음"));

        if (!member.getPassword().equals(authLoginRequestDto.getPassword())) {
            throw new IllegalStateException("비밀번호가 일치하지 않음");
        }

        return new AuthLoginResponseDto(member.getId());
    }
}
