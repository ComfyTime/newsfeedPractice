package org.example.newsfeedPractice.auth.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeedPractice.auth.dto.AuthLoginRequestDto;
import org.example.newsfeedPractice.auth.dto.AuthLoginResponseDto;
import org.example.newsfeedPractice.auth.dto.AuthSignupRequestDto;
import org.example.newsfeedPractice.member.entity.Member;
import org.example.newsfeedPractice.member.repository.MemberRepository;
import org.example.newsfeedPractice.member.util.EmailValidatorUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.example.newsfeedPractice.member.util.PasswordValidatorUtil;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signup(AuthSignupRequestDto authSignupRequestDto) {
        String email = authSignupRequestDto.getEmail();
        String password = authSignupRequestDto.getPassword();

        // 이메일 형식 검증
        if (!EmailValidatorUtil.isValid(email)) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않습니다.");
        }

        // 이메일 중복 검사
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        // 비밀번호 유효성 검사
        if (!PasswordValidatorUtil.isValid(password)) {
            throw new IllegalArgumentException("비밀번호는 최소 10자이며, 숫자, 대문자, 소문자, 특수문자를 모두 포함해야 합니다.");
        }

        Member member = new Member(
                email,
                authSignupRequestDto.getName(),
                password
        );
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
