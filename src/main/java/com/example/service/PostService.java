package com.example.service;

import com.example.dto.PostDTO;

import java.util.List;

public interface PostService {
    void register(PostDTO postDTO );

    List<PostDTO> getAllPosts();

    PostDTO getPostById(int id);

    void updatePost(PostDTO postDTO);

    void deletePostById(int id);


}
