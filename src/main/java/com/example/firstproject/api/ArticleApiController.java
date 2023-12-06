package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  // 로그 표시할 수 있게 해주는 어노테이션
@RestController
@RequestMapping("/api")
public class ArticleApiController {
    @Autowired
    private ArticleService articleService;  // 서비스 객체 주입

    // GET
    @GetMapping("/article") // URL 요청 접수
    public List<Article> index() {  // index() 메서드 정의
        return articleService.index();
    }
    @GetMapping("/article/{id}")    // URL의 id를 매개변수로 받아오기
    public Article show(@PathVariable Long id) {
        return articleService.show(id);
    }

    // POST
    @PostMapping("/articles")    // URL 요청 접수
    public ResponseEntity<Article> create(@RequestBody ArticleDto dto) { // create() 메서드 정의     // @RequestBody 어노테이션 추가해 줘야 한다.  // ResponseEntity<Article> 반환형 수정
        Article created = articleService.create(dto);   // 서비스 호출
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // PATCH
    @PatchMapping("/articles/{id}")
    public ResponseEntity<Article> update(@RequestBody ArticleDto dto, @PathVariable Long id) {  // ResponseEntity<Article> 반환형 수정
        Article updated = articleService.update(id, dto);   // 서비스 호출
        return (updated != null) ?
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // DELETE
    @DeleteMapping("/articles/{id}")    // URL 요청 접수
    public ResponseEntity<Article> delete(@PathVariable Long id, ArticleDto dto) {  // 메서드 정의
        Article deleted = articleService.delete(id);    // 서비스 호출
        return (deleted != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @PostMapping("/transaction-test")
    public ResponseEntity<List<Article>> transactionText(@RequestBody List<ArticleDto> dtos) {
        List<Article> createdList = articleService.createArticles(dtos);    // 서비스 호출
        return (createdList != null) ?  // 생성 결과에 따라 응답 처리
                ResponseEntity.status(HttpStatus.OK).body(createdList):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
