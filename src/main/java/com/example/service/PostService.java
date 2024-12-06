package com.example.service;

import com.example.dto.PostDTO;

import java.util.List;

public interface PostService {
    public void register(String memberId, PostDTO postDTO );

    List<PostDTO> getAllPosts();

    public void updatePosts(PostDTO postDTO);
    public void deletePosts(String memberId,int postId);


}
