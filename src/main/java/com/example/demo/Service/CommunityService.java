package com.example.demo.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.Entity.Community;
import com.example.demo.Repository.CommunityRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommunityService {

    private final CommunityRepository communityRepository;

    /**
     * 커뮤니티 이름으로 커뮤니티를 생성하는 메서드
     */
    @Transactional
    public void createCommunity(String communityName) {
        // 커뮤니티 이름으로 기존 커뮤니티가 있는지 확인
        Optional<Community> existingCommunity = communityRepository.findByCommunityName(communityName);
        if (existingCommunity.isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 커뮤니티입니다.");
        }

        // 새로운 커뮤니티 생성 후 저장
        Community community = new Community();
        community.setCommunityName(communityName);
        communityRepository.save(community);
    }

    /**
     * 커뮤니티 ID로 커뮤니티 조회
     */
    public Community getCommunityById(Long communityId) {
        return communityRepository.findById(communityId)
                .orElseThrow(() -> new IllegalArgumentException("해당 커뮤니티가 존재하지 않습니다."));
    }

    /**
     * 모든 커뮤니티 목록 조회
     */
    public List<Community> getAllCommunities() {
        return communityRepository.findAll();
    }

     /**
     * 커뮤니티 삭제
     */
    @Transactional
    public void deleteCommunity(Long communityId) {
        communityRepository.deleteById(communityId);
    }
}