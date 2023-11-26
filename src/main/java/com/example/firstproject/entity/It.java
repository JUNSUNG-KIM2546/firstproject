package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@AllArgsConstructor // DTO와 연결해주는 생성자[ it() 생성자를 대체하는 어노테이션 ]
@NoArgsConstructor  // 엔티티에 이게 같이 있어야한다 ( 위)
@ToString
public class It {

    @Id // 기본키
    @GeneratedValue //오토인크리먼트
    private  Long id;

    @Column //컬럼
    private String title;

    @Column //컬럼
    private String content;

    
}
