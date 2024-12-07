package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
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
    public ResponseEntity<String> login(@RequestBody MemberDTO memberDTO, HttpSession session) {
        MemberDTO member = memberService.login(memberDTO.getMemberId(), memberDTO.getPassword());
        if (member != null) {
            session.setAttribute("loginUser", member); // 세션에 사용자 정보 저장
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return ResponseEntity.ok("Logout successful");
    }
    // Id 로 멤버 상세조회
    @GetMapping("/{id}")
    public MemberDTO getMemberInfoByMemberId(@PathVariable int id) {
        return memberService.getMemberInfo(id);
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

}
