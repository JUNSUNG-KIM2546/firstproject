package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@Entity // 엔티티 선언
@Getter // 롬복으로 겟터 추가 (불러오는)
@Setter // 롬복으로 셋터 추가 (추가하는)
public class Article {
    @Id //기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 오토인크리먼트 / DB가 id 자동 생성
    private  Long id;

    @Column //컴럼
    private String title;
    @Column
    private String content;

}
