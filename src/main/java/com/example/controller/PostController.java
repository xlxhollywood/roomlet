package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.service.impl.AwsS3Service;
import com.example.service.impl.PostServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final PostServiceImpl postService;
    private final AwsS3Service awsS3Service;

    // TODO : 게시물 등록/수정/삭제/불러오기/자세히 보기/ 필터링
    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestParam("post") String postJson, // JSON 데이터를 String으로 받음
            @RequestParam(value = "multipartFile", required = false) List<MultipartFile> multipartFiles, // 파일은 선택사항
            HttpSession session) {
        try {
            // JSON 데이터를 객체로 변환
            ObjectMapper objectMapper = new ObjectMapper();
            PostDTO postDTO = objectMapper.readValue(postJson, PostDTO.class);

            // 세션에서 로그인된 사용자 정보 가져오기
            MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
            if (loginUser == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first.");
            }

            // 작성자 ID 설정
            postDTO.setMid(loginUser.getId());

            // 파일 업로드 처리
            if (multipartFiles != null && !multipartFiles.isEmpty()) {
                List<String> fileUrls = awsS3Service.uploadFile(multipartFiles); // 다중 파일 업로드
                postDTO.setFileUrl(String.join(",", fileUrls)); // 파일 URL을 ","로 연결하여 저장
            } else {
                // 파일이 없을 경우 빈 문자열 처리
                postDTO.setFileUrl("");
            }

            // 데이터베이스에 저장
            postService.register(postDTO);
            return ResponseEntity.ok("Post created successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
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
        System.out.println("수정 메서드 호출됨");
        MemberDTO loginUser = (MemberDTO) session.getAttribute("loginUser");
        if (loginUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in first.");
        }

        // 게시글 작성자 확인
        int postOwnerId = postService.getPostOwnerId(id);
        if (loginUser.getId() != postOwnerId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("EDIT: UNAUTHORIZED REQUEST.");
        }

        // 기존 데이터
        PostDTO existingPost = postService.getPostById(id);
        if (existingPost == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found.");
        }

        // PostDTO에 mId 값 설정
        postDTO.setMid(existingPost.getMid());

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
        System.out.println("작성자 ID: " + postOwnerId + ", 로그인된 사용자 ID: " + loginUser.getId());

        if (loginUser.getId() != postOwnerId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("DELETE: UNAUTHORIZED REQUEST.");
        }

        // 게시글 삭제
        postService.deletePost(id);
        return ResponseEntity.ok("delete complete");
    }

    @PostMapping("/files")
    public ResponseEntity<?> uploadFile(@RequestParam("multipartFile") List<MultipartFile> multipartFiles) {
        List<String> uploadedFiles = awsS3Service.uploadFile(multipartFiles);
        return ResponseEntity.ok(uploadedFiles);
    }

    // 상태가 REQUESTING_TRANSFER인 게시글 조회
    @GetMapping("/status/requesting")
    public ResponseEntity<List<PostDTO>> getPostsByStatusRequesting() {
        System.out.println("호출됨");
        List<PostDTO> posts = postService.getPostsByStatusRequesting();
        return ResponseEntity.ok(posts);
    }

    // 상태가 TRANSFERRING인 게시글 조회
    @GetMapping("/status/transferring")
    public ResponseEntity<List<PostDTO>> getPostsByStatusTransferring() {
        System.out.println("호출됨");
        List<PostDTO> posts = postService.getPostsByStatusTransferring();
        return ResponseEntity.ok(posts);
    }

    // 상태가 COMPLETED인 게시글 조회
    @GetMapping("/status/completed")
    public ResponseEntity<List<PostDTO>> getPostsByStatusCompleted() {
        System.out.println("호출됨");
        List<PostDTO> posts = postService.getPostsByStatusCompleted();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/search")
    public ResponseEntity<List<PostDTO>> searchPostsByTitle(@RequestParam("keyword") String keyword) {
        log.info("Search keyword: {}", keyword);
        List<PostDTO> posts = postService.searchPostsByTitle(keyword);
        // JSON 반환 데이터 로그 출력
        for (PostDTO post : posts) {
            log.info("Post Data - ID: {}, Title: {}, MemberId: {}, Status: {}, CreateTime: {}",
                    post.getId(),
                    post.getTitle() != null ? post.getTitle() : "No Title",
                    post.getMemberId() != null ? post.getMemberId() : "Unknown User",
                    post.getStatus() != null ? post.getStatus() : "No Status",
                    post.getCreateTime() != null ? post.getCreateTime().toString() : "No Date"
            );
        }

        return ResponseEntity.ok(posts);
    }

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        System.out.println("Test endpoint 호출됨");
        return ResponseEntity.ok("Test successful");
    }

    // 게시글 목록 페이지
    @GetMapping("/list")
    public String getPostList(Model model) {
        List<PostDTO> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
        return "list";
    }

    // 게시글 추가 페이지
    @GetMapping("/create")
    public String createPost() {
        return "create";
    }

    // 게시글 수정 페이지
    @GetMapping("/edit")
    public String editPost(@RequestParam("id") int id, Model model) {
        PostDTO post = postService.getPostById(id);

        System.out.println("Edit Page - File URL: " + post.getFileUrl());
        model.addAttribute("post", post);
        return "edit";
    }

    // 게시글 자세히 보기 페이지
    @GetMapping("/view")
    public String viewPost(@RequestParam("id") int id, Model model) {
        PostDTO post = postService.getPostById(id);

        model.addAttribute("post", post);
        return "view";
    }
}
