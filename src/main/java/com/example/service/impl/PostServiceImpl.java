package com.example.service.impl;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.mapper.MemberMapper;
import com.example.mapper.PostMapper;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final MemberMapper memberProfileMapper;
    @Override
    public void register(PostDTO postDTO) {
        // 작성자 확인
        MemberDTO memberDTO = memberProfileMapper.findMemberById(postDTO.getMId());
        if (memberDTO == null) {
            throw new IllegalArgumentException("Invalid memberId: member not found.");
        }

        // 작성자 ID 및 생성 시간 설정
        postDTO.setMId(memberDTO.getId());
        postDTO.setCreateTime(new Date());

        // 게시물 저장
        postMapper.insertPost(postDTO);
    }



    @Override
    public List<PostDTO> getAllPosts() {
        return postMapper.selectAllPosts();
    }

    @Override
    public PostDTO getPostById(int id) {
        return postMapper.selectPostById(id);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        postMapper.updatePost(postDTO);
    }

    @Override
    public void deletePost(int id) {
         postMapper.deletePostById(id);
    }

    public int getPostOwnerId(int postId) {
        return postMapper.getPostOwnerId(postId); // Mapper 호출
    }
}
