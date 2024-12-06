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
        MemberDTO memberDTO = memberProfileMapper.findMemberById(postDTO.getMId());
        if (memberDTO != null) {
            postDTO.setMId(memberDTO.getId());
            postDTO.setCreateTime(new Date());
            postMapper.insertPost(postDTO);
        } else {
            throw new IllegalArgumentException("Invalid memberId: member not found.");
        }
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
