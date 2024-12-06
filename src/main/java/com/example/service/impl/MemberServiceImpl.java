package com.example.service.impl;

import com.example.dto.MemberDTO;
import com.example.exception.DuplicateIdException;
import com.example.mapper.MemberProfileMapper;
import com.example.service.MemberService;
import com.example.utils.SHA256Util;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.example.utils.SHA256Util.encryptSHA256;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberProfileMapper memberProfileMapper;



    @Override
    public void register(MemberDTO memberDTO) {
        boolean duplIdResult = isDuplicated(memberDTO.getMemberId());
        if (duplIdResult) {
            throw new DuplicateIdException("중복된 아이디입니다.");
        }
        memberDTO.setCreateTime(new Date());
        memberDTO.setPassword(SHA256Util.encryptSHA256(memberDTO.getPassword()));
        int insertCount = memberProfileMapper.register(memberDTO);

        if (insertCount != 1) {
            log.error("insertMember ERROR! {}", memberDTO);
            throw new RuntimeException(
                    "insertUser ERROR! 회원가입 메서드를 확인해주세요\n" + "Params : " + memberDTO);
        }
    }

    @Override
    public MemberDTO login(String id, String password) {
        // 입력된 비밀번호를 SHA-256으로 암호화
        String cryptoPassword = encryptSHA256(password);

        // DB에서 아이디와 암호화된 비밀번호를 조회
        MemberDTO member = memberProfileMapper.findByIdAndPassword(id, cryptoPassword);

        if (member == null) {
            log.error("로그인 실패: ID 또는 비밀번호 불일치 (ID: {})", id);
            throw new RuntimeException("로그인 실패: ID 또는 비밀번호를 확인해주세요.");
        }
        return member;
    }

    @Override
    public boolean isDuplicated(String id) {
        return memberProfileMapper.idCheck(id) > 0;
    }

    @Override
    public MemberDTO getMemberInfo(String memberId) {
        MemberDTO member = memberProfileMapper.getMemberProfile(memberId);
        if (member == null) {
            log.error("회원 정보 조회 실패: 존재하지 않는 회원 (ID: {})", memberId);
            throw new RuntimeException("회원 정보 조회 실패: 존재하지 않는 회원입니다.");
        }
        return member;
    }

    @Override
    public void updatePassword(String memberId, String afterPassword) {
        // 회원 조회
        MemberDTO memberDTO = getMemberInfo(memberId);
        System.out.println("호출됨");
        System.out.println(memberDTO.getMemberId());
        // 비밀번호 업데이트
        memberDTO.setPassword(encryptSHA256(afterPassword));
        int updateCount = memberProfileMapper.updatePassword(memberDTO);
        if (updateCount != 1) {
            log.error("비밀번호 업데이트 실패: {}", memberDTO);
            throw new RuntimeException("비밀번호 업데이트 실패");
        }
    }

    @Override
    public void deleteId(String id, String password) {
        // 회원 조회
        String cryptoPassword = encryptSHA256(password);
        MemberDTO memberDTO = memberProfileMapper.findByIdAndPassword(id, cryptoPassword);
        if (memberDTO == null) {
            log.error("회원 탈퇴 실패: ID 또는 비밀번호 불일치 (ID: {})", id);
            throw new RuntimeException("회원 탈퇴 실패: ID 또는 비밀번호를 확인해주세요.");
        }

        // 회원 삭제
        int deleteCount = memberProfileMapper.deleteMemberProfile(id);
        if (deleteCount != 1) {
            log.error("회원 탈퇴 실패: {}", memberDTO);
            throw new RuntimeException("회원 탈퇴 실패");
        }
        log.info("회원 탈퇴 성공 (ID: {})", id);
    }
}
