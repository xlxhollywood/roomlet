package com.example.mapper;

import com.example.dto.MemberDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
@Mapper
public interface MemberProfileMapper {

    // 멤버 프로필 조회
    MemberDTO getMemberProfile(@Param("memberId") String memberId);

    // 멤버 삭제
    int deleteMemberProfile(@Param("id") String id);

    // 아이디와 비밀번호로 멤버 찾기
    MemberDTO findByIdAndPassword(@Param("memberId") String id, @Param("password") String password);

    // 아이디 중복 체크
    int idCheck(@Param("memberId") String id);

    // 비밀번호 업데이트
    int updatePassword(MemberDTO memberDTO);

    // 주소 업데이트
    int updateAddress(MemberDTO memberDTO);

    int register(MemberDTO memberDTO);
}
