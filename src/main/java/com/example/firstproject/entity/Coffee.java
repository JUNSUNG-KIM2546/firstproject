package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor // DTO와 연결해주는 생성자[ it() 생성자를 대체하는 어노테이션 ]
@NoArgsConstructor  // 엔티티에 이게 같이 있어야한다 ( 위)    // 기본 생성자 추가 어노테이션
@ToString
@Entity // 엔티티 선언
@Getter // 롬복으로 겟터 추가 (불러오는)
@Setter // 롬복으로 셋터 추가 (추가하는)
public class Coffee {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) //오토인크리먼트 //DB가 id 자동 생성
    private  Long id;

    @Column //컬럼
    private String name;

    @Column //컬럼
    private String price;

    public void patch(Coffee coffee) {
        if (coffee.name != null)
            this.name = coffee.name;
        if (coffee.price != null)
            this.price = coffee.price;
    }
}
