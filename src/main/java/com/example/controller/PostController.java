package com.example.controller;

import com.example.dto.PostDTO;
import com.example.dto.response.CommonResponse;
import com.example.service.impl.MemberServiceImpl;
import com.example.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final MemberServiceImpl memberService;
    private final PostServiceImpl postService;

    // TODO : 게시물 등록/수정/삭제/불러오기/자세히 보기/ 필터링
    // 수정/삭제 버튼은 자세히 보기에 있어야함.

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(
            @RequestBody PostDTO postDTO) {
        postService.register(postDTO);
        CommonResponse<PostDTO> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        List<PostDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable int id) {
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody PostDTO postDTO) {
        postDTO.setId(id);
        postService.updatePost(postDTO);
        return ResponseEntity.ok("Post updated successfully");
    }

    @DeleteMapping("/{id}")
    public void deletePostById(@PathVariable int id) {
        postService.deletePostById(id);
    }



}
