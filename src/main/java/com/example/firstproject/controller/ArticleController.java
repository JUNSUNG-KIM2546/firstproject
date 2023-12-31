package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j  // 로깅 기능을 위한 어노테이션 추가
@Controller
@RequestMapping("articles")
public class ArticleController {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }

    @Autowired
    private ArticleRepository articleRepository;  // articleRepository 객체 선언
    
    @Autowired
    private CommentService commentService;  // commentService 서비스 객체 주입
    
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

        return "redirect:/articles/" + saved.getId();   // 추가된 번호의 상세페이지로 리다이렉트
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model){
        log.info("id = " + id); // id를 잘 받았는지 확인하는 로그
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);

        // 2. 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos); // 댓글 목록 모델에 등록

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

    @GetMapping("/{id}/edit")
    String edit(@PathVariable Long id, Model model){
        // 1. 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        // 2. 모델에 데이터 등록
        model.addAttribute("articleEdit", articleEntity);

        // 3. 뷰 페이지 설정하기
        return "articles/edit";
    }
    @PostMapping("/update")
    String update(ArticleDto adto, Model model){
        log.info(adto.toString());
        
        // 1. DTO를 엔티티로 변환하기
        Article articleEntity = adto.toEntity(); // DTO를 엔티티로 변환
        log.info(articleEntity.toString()); // 변환 됐는지 로그 확인
        
        // 2. 엔티티를 DB에 저장
        // 2-1. DB에서 기존 데이터 가져오기
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2-1. 기존 데이터 값을 갱신
        if(target != null){ // 갱신 할 대상 존재 여부 검증
            articleRepository.save(articleEntity);  // 엔티티를 DB에 저장(갱신)
        }
        else{
            return "redirect:/articles";
        }

        // 3. 수정 결과 페이지로 리다이렉트하기
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("{id}/delete")
    String delete(@PathVariable Long id, Model model, RedirectAttributes rttr) {
        // RedirectAttributes rttr 리다이렉트 시점에 한 번만 사용하고 사라지는 휘발성 데이터
        log.info("삭제 요청");

        // 1.삭제할 대상 가져오기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(target.toString());

        // 2.대상 엔티티 삭제하기
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제됐습니다.");
        }

        // 3.결과 페이지로 리다이렉트하기
        return "redirect:/articles/";
    }
}
