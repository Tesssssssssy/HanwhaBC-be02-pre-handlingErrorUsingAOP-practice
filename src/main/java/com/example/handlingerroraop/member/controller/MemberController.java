package com.example.handlingerroraop.member.controller;

import com.example.handlingerroraop.member.model.MemberDto;
import com.example.handlingerroraop.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;
    private final Logger log = LoggerFactory.getLogger(MemberController.class);

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity login(MemberDto memberDto) {
        MemberDto result = memberService.login(memberDto);
        if (result != null) {
            log.info(String.format("login success"));
            return ResponseEntity.ok().body("login success");
        } else {
            log.error(String.format("login fail"));
            return ResponseEntity.status(400).body("login fail");
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/signUp")
    public ResponseEntity signUp(@RequestBody MemberDto memberDto) {
        memberService.signUp(memberDto);
        log.info(String.format("signUp success"));
        return ResponseEntity.ok().body("signUp success");
    }
}
