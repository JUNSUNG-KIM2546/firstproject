package com.example.firstproject.api;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j  // 로그
@RestController // REST 컨트롤러 선언
@RequestMapping("api")
public class CommentApiController {
    @Autowired
    private CommentService commentService;  // 댓글 서비스 객체 주입

    // 1. 댓글 조회
    @GetMapping("/articles/{articleId}/comments")   // 댓글 조회 요청 주소
    public ResponseEntity<List<CommentDto>> comments (@PathVariable Long articleId) {   // comments() 메서드 생성
        // 서비스에 위임
        List<CommentDto> dtos = commentService.comments(articleId);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    // 2. 댓글 생성
    @PostMapping("articles/{articleId}/comments")    // 댓글 생성 요청
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto) {   // create() 메서드 생성
        // 서비스에 위임
        CommentDto createDto = commentService.create(articleId, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(createDto);
    }

    // 3. 댓글 수정
    @PatchMapping("comments/{id}")  // 댓글 수정 요청
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto) {  // update() 메서드 생성
        // 서비스에 위임
        CommentDto updateDto = commentService.update(id, dto);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(updateDto);
    }

    // 4. 댓글 삭제
    @DeleteMapping("comments/{id}") // 댓글 삭제 요청
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {   // delete() 메서드 생성
        // 서비스에 위임
        CommentDto deletedDto = commentService.delete(id);
        // 결과 응답
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }

}
