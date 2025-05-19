package com.example.controller;

import com.example.dto.MemberDTO;
import com.example.dto.PostDTO;
import com.example.service.impl.PostServiceImpl;
import com.example.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class ViewController {

    private final PostServiceImpl postService;
    private final MemberServiceImpl memberService;
    @GetMapping("/list")
    public String getPostsByStatusRequesting(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {
        List<PostDTO> posts;
        String status = "REQUESTING_TRANSFER";

        if (keyword != null && !keyword.trim().isEmpty()) {
            posts = postService.searchPostsByTitleAndStatus(keyword, status);
        } else {
            posts = postService.getPostsByStatusRequesting(); // 전체 게시글
        }

        // mId를 기반으로 memberId 세팅
        for (PostDTO post : posts) {
            if (post.getMid() > -1) {
                MemberDTO member = memberService.getMemberInfo(post.getMid());
                if (member != null) {
                    post.setMemberId(member.getMemberId());
                }
            }
        }

        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword); // 검색어를 JSP에서 유지
        return "list"; // 렌더링할 JSP 파일 이름
    }


    // 상태가 TRANSFERRING인 게시글 조회
    @GetMapping("/transferring")
    public String getPostsByStatusTransferring(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {
        System.out.println("TRANSFERRING 상태 게시글 조회 호출됨");
        List<PostDTO> posts;
        String status = "TRANSFERRING";

        // 검색 키워드가 존재하면 검색 로직 수행
        if (keyword != null && !keyword.trim().isEmpty()) {
            posts = postService.searchPostsByTitleAndStatus(keyword, status);
        } else {
            posts = postService.getPostsByStatusTransferring(); // 전체 조회
        }

        // mId를 기반으로 memberId 세팅
        for (PostDTO post : posts) {
            if (post.getMid() > 0) { // mId가 유효한 경우에만 처리
                MemberDTO member = memberService.getMemberInfo(post.getMid());
                if (member != null) {
                    post.setMemberId(member.getMemberId());
                } else {
                    System.out.println("회원 정보가 없습니다. Post ID: " + post.getId() + ", mId: " + post.getMid());
                }
            }
        }

        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword); // 검색어를 유지하기 위해 JSP에 전달
        return "transfer"; // transfer.jsp 렌더링
    }

    // 모든 글 보여주는 페이지 (헤더 로고와 연결)
    @GetMapping("/list/all")
    public String getAllPosts(
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {
        System.out.println("전체 게시글 조회 호출됨");
        List<PostDTO> posts;

        // 검색 키워드가 존재하면 모든 상태에서 검색
        if (keyword != null && !keyword.trim().isEmpty()) {
            posts = postService.searchPostsByTitle(keyword); // 키워드로 모든 게시글 검색
        } else {
            posts = postService.getAllPosts(); // 모든 게시글 가져오기
        }

        // mId를 기반으로 memberId 세팅
        for (PostDTO post : posts) {
            if (post.getMid() > -1) {
                MemberDTO member = memberService.getMemberInfo(post.getMid());
                if (member != null) {
                    post.setMemberId(member.getMemberId());
                } else {
                    System.out.println("회원 정보가 없습니다. Post ID: " + post.getId() + ", mId: " + post.getMid());
                }
            }
        }

        model.addAttribute("posts", posts);
        model.addAttribute("keyword", keyword);
        System.out.println("Fetched posts: " + posts);
        return "list_all";
    }

}