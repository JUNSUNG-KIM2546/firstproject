package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;  // articleRepository 객체 선언
    
    @GetMapping("/new")
    String articles(Model model){
        return "articles/new";
    }
    @PostMapping("/create")
    String articlesCreate(ArticleDto adto){
        System.out.println(adto.toString());

        // DTO를 엔티티로 변환
        Article article = adto.toEntity();
        System.out.println(article.toString());

        // 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        System.out.println(article.toString());

        return "redirect:/articles/new";
    }
}
