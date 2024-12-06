package com.example.mapper;

import com.example.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper {

    public int register(PostDTO postDTO);

    public void updatePosts(PostDTO postDTO);

    public void deletePosts(int id);

    public List<PostDTO> getAllPosts();
}
