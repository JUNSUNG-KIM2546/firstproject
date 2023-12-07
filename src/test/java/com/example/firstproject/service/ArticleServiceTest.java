package com.example.firstproject.service;

import com.example.firstproject.dto.ArticleDto;
import com.example.firstproject.entity.Article;
import org.junit.jupiter.api.Test;  // Test 패키지 임포트
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

// 앞으로 사용할 수 있는 패키지 임포트
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 해당 클래스를 스프링 부트와 연동해 테스트
class ArticleServiceTest {
    @Autowired
    ArticleService articleService;  // 객체 주입

    @Test   // 해당 메거드가 테스트 코드임을 선언
    void index() {
        // 1. 예상 데이터
        Article a = new Article(1L, "토요일 점심은 쌀국수", "2");
        Article b = new Article(2L, "일요일 점심은 구름계란덮밥", "2");
        Article c = new Article(3L, "월요일 점심은 학식", "6");
        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));    // a,b,c 합치기

        // 2. 실제 데이터
        List<Article> articles = articleService.index();

        // 3. 비교 및 검증   assertEquals(expected.toString(), articles.toString()) 실제데이터와 예상데이터가 일치하는지 비교
        assertEquals(expected.toString(), articles.toString());
    }

    @Test
    void show_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 1L;
        Article expected = new Article(id, "토요일 점심은 쌀국수", "2");

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Test
    void show_실패_존재하지_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.show(id);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }

    @Transactional
    @Test
    void create_성공_title과_content만_있는_dto_입력() {
        // 1. 예상 데이터
        String title = "오늘 저녁은 치킨이닭";   // title과 content 값 임의 배정
        String content = "4";
        ArticleDto dto = new ArticleDto(null, title, content);  // dto 생성
        Article expected = new Article(4L, title, content);
        
        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());  // 비교

    }
    @Test
    void create_실패_id가_포함된_dto_입력() {
        // 1. 예상 데이터
        Long id = 4L;   // id, title, content 값 임의 배정
        String title = "오늘 저녁은 치킨이닭";
        String content = "4";
        ArticleDto dto = new ArticleDto(id, title, content);  // dto 생성
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.create(dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);  // 비교
    }

    @Transactional
    @Test
    void update_성공_존재하는_id와_title_content가_있는_dto_입력() {
        // 1. 예상 데이터
        Long id = 3L;
        String title = "오늘 저녁은 치킨이닭";   // title과 content 값 임의 배정
        String content = "3";
        ArticleDto dto = new ArticleDto(id, title, content);  // dto 생성
        Article expected = new Article(3L, title, content);

        // 2. 실제 데이터
        Article article = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());  // 비교
    }
    @Transactional
    @Test
    void update_실패_존재하지_않는_id와_dto_입력() {
        // 1. 예상 데이터
        Long id = -1L;   // id, title, content 값 임의 배정
        String title = "오늘 저녁은 치킨이닭";
        String content = "3";
        ArticleDto dto = new ArticleDto(id, title, content);  // dto 생성
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.update(id,dto);

        // 3. 비교 및 검증
        assertEquals(expected, article);  // 비교
    }

    @Transactional
    @Test
    void delete_성공_존재하는_id_입력() {
        // 1. 예상 데이터
        Long id = 3L;
        Article expected = new Article(id, "월요일 점심은 학식", "6");

        // 2. 실제 데이터
        Article article = articleService.delete(id);

        // 3. 비교 및 검증
        assertEquals(expected.toString(), article.toString());
    }
    @Transactional
    @Test
    void delete_실패_존재하는_않는_id_입력() {
        // 1. 예상 데이터
        Long id = -1L;
        Article expected = null;

        // 2. 실제 데이터
        Article article = articleService.delete(id);

        // 3. 비교 및 검증
        assertEquals(expected, article);
    }
}