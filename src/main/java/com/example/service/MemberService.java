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
     * @param memberId 사용자 ID
     * @param password 비밀번호
     * @return 로그인된 사용자 정보
     */
    MemberDTO login(String memberId, String password);

    /**
     * ID 중복 체크
     * @param memberId 사용자 Id
     * @return 중복 여부 (true: 중복, false: 중복 아님)
     */
    boolean isDuplicated(String memberId);

    /**
     * 회원 정보 조회
     * @param id 사용자 식별 코드
     * @return 회원 정보
     */
    MemberDTO getMemberInfo(int id);


}
