package com.example.interceptor;

import com.example.dto.MemberDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    public LoginInterceptor() {
        System.out.println("LoginInterceptor Bean Created");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false); // 세션이 존재하지 않으면 null 반환
        String requestURI = request.getRequestURI();

        System.out.println("Interceptor: Request URI: " + requestURI);
        System.out.println("Session: " + (session == null ? "null" : "exists"));

        if (session == null || session.getAttribute("loginUser") == null) {
            // 컨텍스트 경로 가져오기
            String contextPath = request.getContextPath();
            // 로그인 페이지로 리다이렉트
            response.sendRedirect(contextPath + "/login");
            return false;
        }
        return true; // 세션이 있으면 요청 처리 계속 진행
    }

}
