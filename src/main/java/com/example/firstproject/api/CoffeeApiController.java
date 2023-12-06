package com.example.firstproject.api;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.service.CoffeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j // 로그 표시할 수 있게 해주는 어노테이션
@RestController
@RequestMapping("/api")
public class CoffeeApiController {
    @Autowired
    CoffeeService coffeeService;

    //@GET
    @GetMapping("/coffeelist")
    public List<Coffee> list() {
        return coffeeService.list();
    }
    @GetMapping("/coffee{id}")
    public Coffee select(@PathVariable Long id) {
        return coffeeService.select(id);
    }

    //@POST
    @PostMapping("/coffeeinsert")
    public ResponseEntity<Coffee> insert(@RequestBody CoffeeDto dto) { // ResponseEntity<Article> 반환형 수정 // 인서트(POST)할 때 @RequestBody 꼭 필요하다 // 리퀘스트바디를 안해주면 값이 널값으로 들어간다
        Coffee add = coffeeService.add(dto);    // 서비스 호출
        return (add != null) ?
                ResponseEntity.status(HttpStatus.OK).body(add):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    //@PATCH
    @PatchMapping("/coffeeupdate{id}")
    public ResponseEntity<Coffee> update(@RequestBody CoffeeDto dto, @PathVariable Long id) {    // ResponseEntity 엔티티에서 값을 불러와서 수정
        Coffee update = coffeeService.update(id, dto);    // 서비스 호출
        return (update != null)?
                ResponseEntity.status(HttpStatus.OK).body(update):
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        
    }

    //@DELETE
    @DeleteMapping("/coffeedelete{id}")
    public ResponseEntity<Coffee> delete(@PathVariable Long id, CoffeeDto dto) {
        Coffee delete = coffeeService.delete(id);
        return (delete != null) ?
                ResponseEntity.status(HttpStatus.NO_CONTENT).build():
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
