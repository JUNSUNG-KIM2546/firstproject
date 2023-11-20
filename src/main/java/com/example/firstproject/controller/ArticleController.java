package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j  // 로깅 기능을 위한 어노테이션 추가
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
        log.info(adto.toString());
        //System.out.println(adto.toString());

        // DTO를 엔티티로 변환
        Article article = adto.toEntity();
        log.info(article.toString());
        //System.out.println(article.toString());

        // 리파지터리로 엔티티를 DB에 저장
        Article saved = articleRepository.save(article);
        log.info(saved.toString());
        //System.out.println(saved.toString());

        return "redirect:/articles/";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model){
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);

        // 3. 뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/")
    String index(Model model){
        // 1. 모든 데이터 가져오기
        ArrayList<Article> articleEntityList = articleRepository.findAll();

        // 2. 모델에 데이터 등록
        model.addAttribute("articleList", articleEntityList);

        // 3. 뷰 페이지 설정하기
        return "articles/index";
    }
}
