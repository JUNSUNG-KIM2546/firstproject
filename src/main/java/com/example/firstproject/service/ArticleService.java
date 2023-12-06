package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j  // 로그 표시할 수 있게 해주는 어노테이션
@Service    // 서비스 객체 생성
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;    // 게시글 리파지터리 객체 주입

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }


    public Article create(ArticleDto dto) {
        Article article = dto.toEntity();   // dto -> 엔티티로 변환한 후 article에 저장
        if(article.getId() != null) {   // article객체에 id가 존재한다면(null이 아니라면) null을 반환
            return null;
        }
        return articleRepository.save(article); // article을 DB에 저장
    }

    public Article update(Long id, ArticleDto dto) {
        // 1. DTO -> 엔티티 변환하기
        Article article = dto.toEntity();   // dto를 엔티티로 변환
        log.info("id: {}, article: {}", id, article.toString());    // 로그 찍기

        // 2. 타깃 조회하기
        Article target = articleRepository.findById(id).orElse(null);

        // 3. 잘못된 요청 처리하기
        if(target == null || id != article.getId()) {
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());    // 잘못된 요청 로그 찍기
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환    // ResponseEntity 반환 (ResponseEntity<Article>) 으로 해줘야 한다. 
        }

        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);  // 기존 데이터에 새 데이터 붙이기(일부 수정)
        Article updated = articleRepository.save(target);  // article 엔티티 DB에 저장
        log.info("수정 완료! id: {}, article: {}", id, article.toString());    // 수정 완료 로그 찍기
        return  updated;    // 응답은 컨트롤러가 하므로 여기서는 수정 데이터만 반환
    }

    public Article delete(Long id) {
        // 1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        // 2. 잘못된 요청 처리하기
        if(target == null) {
            return null;    // 응답은 컨트롤러가 하므로 여기서는 null 반환
        }

        // 3. 대상 삭제하기
        articleRepository.delete(target);
        log.info("삭제 완료!");    // 삭제 완료 로그 찍기
        //return ResponseEntity.status(HttpStatus.OK).body(null);   // .status(HttpStatus.OK).body(null) 이거 대신 .status(HttpStatus.OK).build();로 작성해도 된다
        return  target; // DB에서 삭제한 대상을 컨트롤러에 반환
    }

    @Transactional  // 트랜잭션 어노테이션 (예외처리 되면 롤백)
    public List<Article> createArticles(List<ArticleDto> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환(스트림 문법)
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        /* for문 방법
        List<Article> articleList = new ArrayList<>();
        for(int i=0; i<dtos.size(); i++){
            ArticleDto dto = dtos.get(i);
            Article entity = dto.toEntity();
            articleList.add(entity);
        }*/

        // 2. 엔티티 묶음을 DB에 저장(스트림 문법)
        articleList.stream().forEach(article -> articleRepository.save(article));
        /* for문 방법
        for(int i=0; i<articleList.size(); i++){
            Article article = articleList.get(i);
            articleRepository.save(article);
        }*/

        // 3. 강제 예외 발생시키기
        articleRepository.findById(-1L).orElseThrow(() -> new IllegalArgumentException("결제 실패"));    // id가 -1인 데이터 찾기 .찾는 데이터가 없으면 예외 발생
        
        // 4. 결과 값 반환하기
        return articleList;
    }
}
