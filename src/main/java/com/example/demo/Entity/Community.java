package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Community {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String communityName;

    // 커뮤니티에 속한 게시글 리스트 (양방향 관계)
    @OneToMany(mappedBy = "community")
    private List<Post> posts;
}
