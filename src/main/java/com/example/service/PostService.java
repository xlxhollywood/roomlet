package com.example.service;

import com.example.dto.PostDTO;

import java.util.List;

public interface PostService {
    void register(PostDTO postDTO );

    List<PostDTO> getAllPosts();

    PostDTO getPostById(int id);

    void updatePost(PostDTO postDTO);

    void deletePost(int id); // 로그인 사용자 ID를 추가로 전달

    List<PostDTO> searchPostsByTitle(String keyword);

}
