package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Community;
import com.example.demo.Service.CommunityService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class CommunityController {

    private final CommunityService communityService;

    /**
     * 커뮤니티 목록 페이지로 이동
     */
    @GetMapping("/list")
    public String listCommunities(Model model) {
        // 모든 커뮤니티를 조회하여 모델에 추가
        List<Community> communities = communityService.getAllCommunities();
        model.addAttribute("communities", communities);
        return "community_list"; // community_list.html로 이동
    }

    /**
     * 커뮤니티 생성 페이지로 이동
     */
    @GetMapping("/create")
    public String createCommunityForm() {
        return "community_create"; // create_community.html로 이동
    }

    /**
     * 커뮤니티 생성 요청 처리
     */
    @PostMapping("/create")
    public String createCommunity(@RequestParam("communityName") String communityName) {
        // 커뮤니티 생성 서비스 호출
        communityService.createCommunity(communityName);
        return "redirect:/community/list"; // 커뮤니티 목록으로 리다이렉트
    }

    /**
     * 커뮤니티 삭제 요청 처리
     * @param communityId 삭제할 커뮤니티의 ID
     */
    @PostMapping("/delete/{communityId}")
    public String deleteCommunity(@PathVariable Long communityId) {
        // 커뮤니티 삭제 서비스 호출
        communityService.deleteCommunity(communityId);
        return "redirect:/community/list"; // 커뮤니티 목록으로 리다이렉트
    }
}
