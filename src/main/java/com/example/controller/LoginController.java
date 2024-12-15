package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("로그인 호출됨");
        return "login";
    }

    // login 과정에서 세션 설정해줘야함.
    @PostMapping("/loginOk")
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
}
