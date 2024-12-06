package com.example.service.impl;

import com.example.dto.MemberDTO;
import com.example.exception.DuplicateIdException;
import com.example.mapper.MemberMapper;
import com.example.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberProfileMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    // TODO: 회원 가입, 로그인, ID 중복 체크, ID(식별코드) 를 이용한 회원 정보 가져오기.
    @Override
    public void register(MemberDTO memberDTO) {
        boolean duplIdResult = isDuplicated(memberDTO.getMemberId());
        if (duplIdResult) {
            throw new DuplicateIdException("중복된 아이디입니다.");
        }

        String encryptedPassword = passwordEncoder.encode(memberDTO.getPassword());
        memberDTO.setPassword(encryptedPassword);

        memberDTO.setCreateTime(new Date());

        memberProfileMapper.createMember(memberDTO);
    }

    @Override
    public boolean isDuplicated(String memberId) {
        return memberProfileMapper.idCheck(memberId) > 0;
    }

    @Override
    public MemberDTO login(String memberId, String password) {
        // DB에서 해당 ID의 사용자 정보 조회
        MemberDTO member = memberProfileMapper.findMemberByMemberId(memberId);

        if (member == null) {
            throw new RuntimeException("로그인 실패: ID를 확인해주세요.");
        }

        // DB에 저장된 암호화된 비밀번호
        String dbPassword = member.getPassword();

        System.out.println("로그인 시 입력된 비밀번호: " + password);
        System.out.println("DB에 저장된 암호화 비밀번호: " + dbPassword);
        System.out.println("비밀번호 일치 여부: " + passwordEncoder.matches(password, dbPassword));

        // 입력된 비밀번호와 암호화된 비밀번호 비교
        if (!passwordEncoder.matches(password, dbPassword)) {
            throw new RuntimeException("로그인 실패: 비밀번호가 일치하지 않습니다.");
        }

        System.out.println("로그인 성공 - 사용자 ID: " + memberId);
        return member;
    }


    @Override
    public MemberDTO getMemberInfo(int id) {
        MemberDTO member = memberProfileMapper.findMemberById(id);
        if (member == null) {
            log.error("회원 정보 조회 실패: 존재하지 않는 회원 (ID: {})", id);
            throw new RuntimeException("회원 정보 조회 실패: 존재하지 않는 회원입니다.");
        }
        return member;
    }


}
