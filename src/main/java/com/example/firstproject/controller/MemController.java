package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.MemDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Mem;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.MemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("mem")
public class MemController {

    @Autowired
    private MemRepository memRepository;  // memRepository 객체 선언
    
    @GetMapping("/signup")
    String signup(Model model){
        return "mem/signup";
    }
    @PostMapping("/signup")
    String signup(MemDto mdto){
        // DTO를 엔티티로 변환
        Mem mem = mdto.toEntity();

        // 리파지터리로 엔티티를 DB에 저장
        Mem saved = memRepository.save(mem);

        return "redirect:/mem/signup";
    }
}
