package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity // 엔티티 선언
public class Mem {
    @Id //기본키
    @GeneratedValue // 오토인크리먼트
    private  Long id;

    @Column //컴럼
    private String email;
    @Column
    private String pass;

    public Mem(Long id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }

}
