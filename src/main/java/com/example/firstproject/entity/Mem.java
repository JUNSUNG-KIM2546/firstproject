package com.example.firstproject.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@ToString
@Entity // 엔티티 선언
@AllArgsConstructor
@NoArgsConstructor
@Getter // 겟터 (가져오기)
@Setter // 셋터 (추가하기)
public class Mem {

    @Id //기본키
    @GeneratedValue // 오토인크리먼트
    private  Long id;

    @Column //컴럼
    private String email;
    @Column
    private String pass;

    /*public Mem(Long id, String email, String pass) {
        this.id = id;
        this.email = email;
        this.pass = pass;
    }*/

}
