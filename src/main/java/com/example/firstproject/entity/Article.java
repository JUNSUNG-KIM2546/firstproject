package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // 엔티티 선언
public class Article {
    @Id //기본키
    @GeneratedValue // 오토인크리먼트
    private  Long id;

    @Column //컴럼
    private String tilte;
    @Column
    private String content;

    public Article(Long id, String tilte, String content) {
        this.id = id;
        this.tilte = tilte;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", tilte='" + tilte + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
