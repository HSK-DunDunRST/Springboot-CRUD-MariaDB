package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Community;
import com.example.demo.Entity.Post;
import com.example.demo.Service.CommunityService;
import com.example.demo.Service.PostService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community/{communityId}")
public class PostController {

    private final PostService postService;
    private final CommunityService communityService;

    // 특정 커뮤니티의 게시글 목록 페이지 이동
    @GetMapping("/list")
    public String listPosts(@PathVariable Long communityId, Model model) {
        List<Post> posts = postService.getPostsByCommunity(communityId);
        Community community = communityService.getCommunityById(communityId); // 커뮤니티 정보를 가져옴
        model.addAttribute("posts", posts);
        model.addAttribute("communityId", communityId); // 커뮤니티 ID 전달
        model.addAttribute("communityName", community.getCommunityName()); // 커뮤니티 이름 전달
        return "post_list"; // post_list.html로 이동
    }

    // 게시글 작성 페이지 이동
    @GetMapping("/create")
    public String createPostForm(@PathVariable Long communityId) {
        return "post_create"; // create_post.html로 이동
    }

    // 게시글 생성 요청 처리
    @PostMapping("/create")
    public String createPost(@PathVariable Long communityId,
                             @RequestParam("title") String title,
                             @RequestParam("content") String content) {
        System.out.println("Creating community with ID: " + communityId);
        postService.createPost(communityId, title, content);
        return "redirect:/community/" + communityId + "/list";  // 게시글 목록으로 리다이렉트
    }

    // 게시글 수정 페이지 이동
    @GetMapping("/modify/{postId}")
    public String modifyPost(@PathVariable Long communityId, @PathVariable Long postId, Model model){
        Post post = postService.getPost(postId).orElseThrow(() -> new IllegalArgumentException("해당 게시물은 존재하지 않습니다"));
        Community community = communityService.getCommunityById(communityId);
        // System.out.println("Getting Post ID: " + post.getId());
        // System.out.println("Getting Post Title: " + post.getTitle());
        // System.out.println("Getting Post Content: " + post.getContent());
        model.addAttribute("post", post);
        model.addAttribute("communityId", communityId);
        model.addAttribute("communityName", community.getCommunityName());
        return "post_modify";
    }


    @PostMapping("/modify/{postId}")
    public String modifyPost(@PathVariable Long communityId, @PathVariable Long postId,
                             @RequestParam String title, @RequestParam String content){
        
        postService.modifyPost(postId, title, content);
        return "redirect:/community/" + communityId + "/list";
    }

    // 게시글 삭제 요청 처리
    @GetMapping("/delete/{postId}")
    public String deletePost(@PathVariable Long communityId, @PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/community/" + communityId + "/list"; // 삭제 후 게시글 목록으로 리다이렉션
    }
}
