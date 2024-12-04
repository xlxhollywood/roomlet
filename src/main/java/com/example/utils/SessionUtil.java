package com.example.utils;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_MEMBER_ID = "LOGIN_MEMBER_ID";
    private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";

    // 기본 생성자를 private로 설정하여 인스턴스화를 방지
    private SessionUtil() {
    }

    /**
     * 세션에서 로그인한 멤버 ID를 가져옵니다.
     * @param session HttpSession 객체
     * @return 로그인한 멤버 ID
     */
    public static String getLoginMemberId(HttpSession session) {
        return (String) session.getAttribute(LOGIN_MEMBER_ID);
    }

    /**
     * 세션에 로그인한 멤버 ID를 설정합니다.
     * @param session HttpSession 객체
     * @param id 멤버 ID
     */
    public static void setLoginMemberId(HttpSession session, String id) {
        session.setAttribute(LOGIN_MEMBER_ID, id);
    }

    /**
     * 세션에서 로그인한 관리자 ID를 가져옵니다.
     * @param session HttpSession 객체
     * @return 로그인한 관리자 ID
     */
    public static String getLoginAdminId(HttpSession session) {
        return (String) session.getAttribute(LOGIN_ADMIN_ID);
    }

    /**
     * 세션에 로그인한 관리자 ID를 설정합니다.
     * @param session HttpSession 객체
     * @param id 관리자 ID
     */
    public static void setLoginAdminId(HttpSession session, String id) {
        session.setAttribute(LOGIN_ADMIN_ID, id);
    }

    /**
     * 세션을 무효화하여 모든 데이터를 삭제합니다.
     * @param session HttpSession 객체
     */
    public static void clear(HttpSession session) {
        session.invalidate();
    }
}
