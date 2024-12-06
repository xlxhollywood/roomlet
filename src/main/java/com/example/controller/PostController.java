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

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
@Log4j2
public class PostController {
    private final MemberServiceImpl memberService;
    private final PostServiceImpl postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommonResponse<PostDTO>> registerPost(
            @RequestBody PostDTO postDTO) { // memberId는 postDTO 내부에서 처리
        postService.register(postDTO.getMemberId(), postDTO);
        CommonResponse<PostDTO> commonResponse = new CommonResponse<>(HttpStatus.OK, "SUCCESS", "registerPost", postDTO);
        return ResponseEntity.ok(commonResponse);
    }


}
