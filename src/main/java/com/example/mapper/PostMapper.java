package com.example.mapper;

import com.example.dto.PostDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostMapper {

    void insertPost(PostDTO postDTO);
    PostDTO selectPostById(int id);
    void updatePost(PostDTO postDTO);
    void deletePostById(int id);
    List<PostDTO> selectAllPosts();
    int getPostOwnerId(int postId);

    String getFileUrlByPostId(@Param("postId") int postId);

    // 상태가 REQUESTING_TRANSFER인 게시글 조회
    List<PostDTO> selectPostsByStatusRequesting();

    // 상태가 TRANSFERRING인 게시글 조회
    List<PostDTO> selectPostsByStatusTransferring();

    // 상태가 COMPLETED인 게시글 조회
    List<PostDTO> selectPostsByStatusCompleted();

    // 제목에 특정 글자가 포함된 게시물 검색
    List<PostDTO> searchPostsByTitle(@Param("keyword") String keyword);


}
