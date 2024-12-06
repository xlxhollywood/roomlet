package com.example.service.impl;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.mapper.MemberProfileMapper;
import com.example.mapper.PostMapper;
import com.example.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final MemberProfileMapper memberProfileMapper;
    @Override
    public void register(String memberId, PostDTO postDTO) {
        MemberDTO memberDTO = memberProfileMapper.getMemberProfile(memberId);
        if (memberDTO != null) {
            postDTO.setMemberId(memberDTO.getMemberId());
            postDTO.setCreateTime(new Date());
            postMapper.register(postDTO);
        } else {
            throw new IllegalArgumentException("Invalid memberId: member not found.");
        }
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postMapper.getAllPosts();
    }

    @Override
    public void updatePosts(PostDTO postDTO) {
        if(postDTO != null && postDTO.getId() != 0){
            postMapper.updatePosts(postDTO);
        } else {
            log.error("updatePosts ERROR {}", postDTO);
            throw new RuntimeException("updatePosts ERROR! 게시글 수정 메서드 확인 해주세요" + postDTO);

        }
    }

    @Override
    public void deletePosts(String memberId, int postId) {
        if(memberId != null && postId != 0){
            postMapper.deletePosts(postId);
        } else {
            log.error("deletePosts ERROR {}", postId);
            throw new RuntimeException("deletePosts ERROR! 게시글 삭제 메서드 확인 해주세요" + postId);
        }
    }
}
