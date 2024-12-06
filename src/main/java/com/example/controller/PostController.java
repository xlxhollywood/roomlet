package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.service.impl.PostServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final PostServiceImpl postService;

    // TODO : 게시물 등록/수정/삭제/불러오기/자세히 보기/ 필터링
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO postDTO, HttpSession session) {
        // 세션에서 로그인된 사용자 정보 가져오기
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        System.out.println("로그인 사용자 정보: " + loginUser);
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first.");
        }

        // 작성자 ID를 PostDTO에 설정
        postDTO.setMId(loginUser.getId());

        // 글 작성 로직 실행
        postService.register(postDTO);
        return ResponseEntity.ok("Post created successfully.");
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
    public ResponseEntity<String> updatePost(@PathVariable int id, @RequestBody PostDTO postDTO, HttpSession session) {
        // 로그인 사용자 정보 가져오기
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first.");
        }

        // 게시글 작성자 확인
        int postOwnerId = postService.getPostOwnerId(id); // DB에서 작성자 ID 조회
        if (loginUser.getId() != postOwnerId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("EDIT : UNAUTHORIZED REQUEST.");
        }

        // 게시글 수정
        postDTO.setId(id);
        postService.updatePost(postDTO);
        return ResponseEntity.ok("edit complete");
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id, HttpSession session) {
        // 로그인 사용자 정보 가져오기
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first.");
        }

        // 게시글 작성자 확인
        int postOwnerId = postService.getPostOwnerId(id); // DB에서 작성자 ID 조회
        if (loginUser.getId() != postOwnerId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("DELETE: UNAUTHORIZED REQUEST.");
        }

        // 게시글 삭제
        postService.deletePost(id);
        return ResponseEntity.ok("delete complete");
    }



}
