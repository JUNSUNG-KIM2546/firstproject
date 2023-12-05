package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class ArticleApiController {
    @Autowired  // 게시글 레파지터리 주입
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/article") // URL 요청 접수
    public List<Article> index() {  // index() 메서드 정의
        return articleRepository.findAll();
    }
    @GetMapping("/article/{id}")    // URL의 id를 매개변수로 받아오기
    public Article show(@PathVariable Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/articles")    // URL 요청 접수
    public Article create(@RequestBody ArticleDto dto) { // create() 메서드 정의     // @RequestBody 어노테이션 추가해 줘야 한다.
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleDto dto, @PathVariable Long id) {  // ResponseEntity<Article> 반환형 수정
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();   // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());    // 로그 찍기
        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()) {
            // 400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());    // 로그 찍기
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);    // ResponseEntity 반환 (ResponseEntity<Article>) 으로 해줘야 한다.
        }
        // 4. 업데이트 및 정상 응답(200)하기
        Article updated = articleRepository.save(article);
        return  ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
}
