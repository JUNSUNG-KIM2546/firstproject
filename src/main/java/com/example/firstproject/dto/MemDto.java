package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Mem;

public class MemDto {

    private String email;
    private String pass;

    public MemDto(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public Mem toEntity() {
        return new Mem(null, email, pass);
    }
}
