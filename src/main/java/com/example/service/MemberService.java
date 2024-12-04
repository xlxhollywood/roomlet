package com.example.service;

import com.example.dto.MemberDTO;

public interface MemberService {

    /**
     * 회원 등록
     * @param memberDTO 회원 정보
     */
    void register(MemberDTO memberDTO);

    /**
     * 로그인
     * @param id 사용자 ID
     * @param password 비밀번호
     * @return 로그인된 사용자 정보
     */
    MemberDTO login(String id, String password);

    /**
     * ID 중복 체크
     * @param id 사용자 ID
     * @return 중복 여부 (true: 중복, false: 중복 아님)
     */
    boolean isDuplicated(String id);

    /**
     * 회원 정보 조회
     * @param memberId 사용자 ID
     * @return 회원 정보
     */
    MemberDTO getMemberInfo(String memberId);

    /**
     * 비밀번호 변경
     * @param memberId 사용자 ID
     * @param afterPassword 변경할 비밀번호
     */
    void updatePassword(String memberId, String afterPassword);

    /**
     * 회원 탈퇴
     * @param id 사용자 ID
     * @param password 사용자 비밀번호
     */
    void deleteId(String id, String password);
}
