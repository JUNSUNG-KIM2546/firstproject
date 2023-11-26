package com.example.firstproject.dto;

import com.example.firstproject.entity.Mem;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor // 생성자 어노테이션
public class MemDto {

    private Long id;
    private String email;
    private String pass;

   /* public MemDto(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }*/

    public Mem toEntity() {
        return new Mem(id, email, pass);
    }
}
