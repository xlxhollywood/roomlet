package com.example.service.impl;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.mapper.MemberMapper;
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
    private final MemberMapper memberProfileMapper;
    @Override
    public void register(PostDTO postDTO) {
        // 작성자 확인
        MemberDTO memberDTO = memberProfileMapper.findMemberById(postDTO.getMid());
        if (memberDTO == null) {
            throw new IllegalArgumentException("Invalid memberId: member not found.");
        }

        // 작성자 ID 및 생성 시간 설정
        postDTO.setMid(memberDTO.getId());
        postDTO.setCreateTime(new Date());

        // 게시물 저장
        postMapper.insertPost(postDTO);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        List<PostDTO> posts = postMapper.selectAllPosts();

        for (PostDTO post : posts) {
            MemberDTO member = memberProfileMapper.findMemberById(post.getMid());
            if (member != null) {
                post.setMemberId(member.getMemberId());
            } else {
                post.setMemberId("Unknown");
            }
        }
        return posts;
    }

    @Override
    public PostDTO getPostById(int id) {
        return postMapper.selectPostById(id);
    }

    @Override
    public void updatePost(PostDTO postDTO) {
        postDTO.setUpdateTime(new Date());

        // 기존 게시글 정보 가져오기
        PostDTO existingPost = postMapper.selectPostById(postDTO.getId());
        if (existingPost == null) {
            throw new IllegalArgumentException("Post not found with ID: " + postDTO.getId());
        }

        // 기존 fileUrl 유지
        if (postDTO.getFileUrl() == null || postDTO.getFileUrl().isEmpty()) {
            postDTO.setFileUrl(existingPost.getFileUrl());
        }

        postMapper.updatePost(postDTO);
    }

    @Override
    public void deletePost(int id) {
         postMapper.deletePostById(id);
    }

    public int getPostOwnerId(int postId) {
//        return postMapper.getPostOwnerId(postId); // Mapper 호출
        int ownerId = postMapper.getPostOwnerId(postId);
        System.out.println("디버깅: postId = " + postId + ", ownerId = " + ownerId);
        return ownerId;
    }

    public String getFileUrlByPostId(int postId) {
        return postMapper.getFileUrlByPostId(postId);
    }

    // 상태가 REQUESTING_TRANSFER인 게시글 조회
    public List<PostDTO> getPostsByStatusRequesting() {
        return postMapper.selectPostsByStatusRequesting();
    }

    // 상태가 TRANSFERRING인 게시글 조회
    public List<PostDTO> getPostsByStatusTransferring() {
        return postMapper.selectPostsByStatusTransferring();
    }

    public List<PostDTO> getPostsByStatusCompleted() {
        return postMapper.selectPostsByStatusCompleted();
    }

    @Override
    public List<PostDTO> searchPostsByTitle(String keyword) {
        return postMapper.searchPostsByTitle(keyword);
    }

}
