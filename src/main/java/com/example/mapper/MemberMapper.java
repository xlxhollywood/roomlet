package com.example.mapper;

import com.example.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MemberMapper {

    /**
     * 사용자 생성
     * @param member MemberDTO 객체
     */
    void createMember(MemberDTO member);

    /**
     * 특정 ID로 사용자 조회
     * @param id 사용자 ID
     * @return MemberDTO
     */
    MemberDTO findMemberById(@Param("id") int id);

    MemberDTO findMemberByMemberId(@Param("memberId")String memberId);

    /**
     * 모든 사용자 조회
     * @return 사용자 목록
     */
    List<MemberDTO> getAllMembers();

    /**
     * 사용자 정보 업데이트
     * @param member MemberDTO 객체
     */
    void updateMember(MemberDTO member);

    /**
     * 특정 ID로 사용자 삭제
     * @param id 사용자 ID
     */
    void deleteMember(@Param("id") int id);

    int idCheck(@Param("memberId") String memberId);

    // 아이디와 비밀번호로 멤버 찾기
    MemberDTO findByIdAndPassword(@Param("memberId") String id, @Param("password") String password);
}
