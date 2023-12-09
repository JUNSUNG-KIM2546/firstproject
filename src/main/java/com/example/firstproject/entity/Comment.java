package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 해당 클래스가 엔티티임을 선언, 클래스 필드를 바탕으로 DB에 테이블 생성
@Getter // 각 필드 값을 조회할 수 있는 getter 메서드 자동 생성
@ToString   // 모든 필드를 출력할 수 있는 toString 메서드 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자 자동 생성
@NoArgsConstructor  // 매개변수가 아예 없는 기본 생성자 자동 생성
public class Comment {

    @Id // 대표키 지정
    @GeneratedValue(strategy=GenerationType.IDENTITY) // DB가 자동으로 1씩 증가(오토인크리먼트)
    private Long id;    // 대표키 = 기본키 = PK

    @ManyToOne  // Comment 엔티티와 Article 엔티티를 다대일 관계로 설정
    @JoinColumn(name="article_id")    // 외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Article article;    // 해당 댓글의 부모 게시글

    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String nickname;    // 댓글을 단 사람

    @Column // 해당 필드를 테이블의 속성으로 매핑
    private String body;    // 댓글 본문
}
