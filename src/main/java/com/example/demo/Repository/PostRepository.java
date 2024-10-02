package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Post;

import java.util.List;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // 특정 커뮤니티에 속한 게시글 목록을 조회하는 메서드
    List<Post> findByCommunityId(Long communityId);
}
