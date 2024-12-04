package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.mapper.MemberProfileMapper;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    // .jsp 에서 받을 경우 @ModelAttribute로 받아야함..
    @PostMapping("/register")
    public void register(@RequestBody MemberDTO memberDTO) {
        // DTO에서 필드를 분리해서 register 메소드에 넘깁니다.
        memberService.register(memberDTO);
    }
    // login 과정에서 세션 설정해줘야함.
    // .jsp 에서 받을 경우 @ModelAttribute로 받아야함..
    @PostMapping("/login")
    public MemberDTO login(@RequestBody MemberDTO memberDTO) {
        return memberService.login(memberDTO.getMemberId(), memberDTO.getPassword());
    }


    @GetMapping("/{id}")
    public MemberDTO getMemberInfo(@PathVariable String id) {
        return memberService.getMemberInfo(id);
    }

    @PutMapping("/{id}/password")
    public String updatePassword(@PathVariable String id, @RequestParam String newPassword) {
        memberService.updatePassword(id, newPassword);
        return "비밀번호 변경 성공";
    }

    @DeleteMapping("/{id}")
    public String deleteId(@PathVariable String id, @RequestParam String password) {
        memberService.deleteId(id, password);
        return "회원 탈퇴 성공";
    }
}
