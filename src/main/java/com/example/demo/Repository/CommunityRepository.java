package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Community;

import java.util.Optional;


@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    // 커뮤니티 이름으로 커뮤니티를 조회하는 메서드
    Optional<Community> findByCommunityName(String communityName);
}