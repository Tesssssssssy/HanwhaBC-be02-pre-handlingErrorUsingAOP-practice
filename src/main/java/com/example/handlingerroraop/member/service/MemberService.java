package com.example.handlingerroraop.member.service;

import com.example.handlingerroraop.exception.GlobalExceptionAdvice;
import com.example.handlingerroraop.member.model.Member;
import com.example.handlingerroraop.member.model.MemberDto;
import com.example.handlingerroraop.member.repository.MemberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService {
    private MemberRepository memberRepository;
    private final Logger log = LoggerFactory.getLogger(MemberService.class);

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void signUp(MemberDto memberDto) {
        memberRepository.save(Member.builder()
                        .email(memberDto.getEmail())
                        .password(memberDto.getPassword())
                .build());
        log.info(String.format("회원가입 완료"));
    }

    public MemberDto login(MemberDto memberDto) {
        Optional<Member> result = memberRepository.findByEmail(memberDto.getEmail());
        if (result.isPresent()) {
            Member member = result.get();
            if (member.getPassword().equals(memberDto.getPassword())) {
                MemberDto memberDto1 = MemberDto.builder()
                        .id(member.getId())
                        .email(member.getEmail())
                        .password(member.getPassword())
                        .build();
                log.info(String.format("DB에서 유저 정보 조회 후 반환"));
                return memberDto1;
            } else {
                log.error(String.format("비밀번호 불일치"));
                return null;
            }
        } else {
            log.error(String.format("유저 존재 x"));
            return null;
        }
    }
}
