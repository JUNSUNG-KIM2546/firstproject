package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가 어노테이션
@Entity // 엔티티 선언
public class Article {
    @Id //기본키
    @GeneratedValue // 오토인크리먼트
    private  Long id;

    @Column //컴럼
    private String title;
    @Column
    private String content;

}
