package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    // TODO: 회원 가입 / 로그인 / ID(식별코드) 를 이용한 회원 정보 가져오기.
    // TODO: .jsp 에서 받을 경우 @ModelAttribute로 받아야함..
    @PostMapping("/register")
    public void register(@RequestBody MemberDTO memberDTO) {
        // DTO에서 필드를 분리해서 register 메소드에 넘깁니다.
        memberService.register(memberDTO);
    }

    // login 과정에서 세션 설정해줘야함.
    @PostMapping("/login")
    public MemberDTO login(@RequestBody MemberDTO memberDTO) {
        return memberService.login(memberDTO.getMemberId(), memberDTO.getPassword());
    }

    // Id 로 멤버 상세조회
    @GetMapping("/{id}")
    public MemberDTO getMemberInfoByMemberId(@PathVariable int id) {
        return memberService.getMemberInfo(id);
    }

}
