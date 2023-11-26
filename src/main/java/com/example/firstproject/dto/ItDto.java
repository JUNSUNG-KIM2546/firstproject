package com.example.firstproject.dto;

import com.example.firstproject.entity.It;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor // itDto() 생성자를 대체하는 어노테이션
public class ItDto {
    private String title;
    private String content;


    public It toEntity() {
        return new It(null, title, content);
    }
}
