package com.example.firstproject.repository;

import com.example.firstproject.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {   // JpaRepository<대상_엔티티, 대표키_값의_타입>
    // 특정 게시판의 모든 댓글 조회
    @Query(value = "SELECT * FROM comment WHERE article_id = :articleId", nativeQuery = true)   // value 속성에 실행하려는 쿼리 작성  
    List<Comment> findByArticleId(Long articleId);  // 네이티브 쿼리 메서드

    // 특정 닉네임의 모든 댓글 조희
    List<Comment> findByNickname(String nickname);
}
