package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.Community;
import com.example.demo.Entity.Post;
import com.example.demo.Repository.PostRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommunityService communityService;

    // 특정 커뮤니티에 있는 전체 게시글 조회 서비스
    public List<Post> getPostsByCommunity(Long communityId) {
        return postRepository.findByCommunityId(communityId);
    }

    // 특정 게시글 단일 조회 서비스 (수정용)
    @Transactional
    public Optional<Post> getPost(Long postId){
        return  postRepository.findById(postId);
    }

    // 특정 커뮤니티에 게시글 생성 서비스
    @Transactional
    public void createPost(Long communityId, String title, String content) {
        // 커뮤니티 ID로 커뮤니티 조회
        Community community = communityService.getCommunityById(communityId);

        // 새로운 게시글 생성 후 저장
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCommunity(community);

        postRepository.save(post);
    }

    // 게시글 삭제 서비스
    @Transactional
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    // 게시글 수정 서비스
    @Transactional
    public void modifyPost(Long postId, String title, String content){
        Optional<Post> postOptional = postRepository.findById(postId);
        if(postOptional.isPresent()){
            Post post = postOptional.get();
            post.setTitle(title);
            post.setContent(content);
            postRepository.save(post);
        } else {
            throw new IllegalArgumentException("해당 게시글을 찾을 수 없습니다.");
        }
    }
}