package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    // 게시글이 속한 커뮤니티와의 연관 관계 (다대일 관계)
    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;
}