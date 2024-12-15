package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.exception.DuplicateIdException;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String register(@ModelAttribute MemberDTO memberDTO, HttpSession session, Model model) {
        // DTO에서 필드를 분리해서 register 메소드에 넘깁니다.
        try {
            memberService.register(memberDTO);
            session.setAttribute("message", "Welcome to Rentor!");
            return "redirect:/login";
        } catch (DuplicateIdException e) {
            // 모델에 에러메세지 추가
            model.addAttribute("errorMessage", e.getMessage());
            return "register";
        }
    }

    // Id 로 멤버 상세조회
    @GetMapping("/{id}")
    public MemberDTO getMemberInfoByMemberId(@PathVariable int id) {
        return memberService.getMemberInfo(id);
    }

    // 회원가입 페이지
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

}
