package com.example.firstproject.service;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j  // 로그 표시할 수 있게 해주는 어노테이션
@Service    // 서비스 객체 생성
public class CoffeeService {
    @Autowired
    CoffeeRepository coffeeRepository;

    public List<Coffee> list() {
        return coffeeRepository.findAll();
    }

    public Coffee select(Long id) {
        return coffeeRepository.findById(id).orElse(null);
    }

    public Coffee add(CoffeeDto dto) {
        Coffee coffee = dto.toEntity(); // dto -> 엔티티로 변환한 후 coffeedp 저장
        if(coffee.getId() != null){     //객체에 id가 존재한다면(null이 아니라면) null을 반환
            return null;
        }
        return coffeeRepository.save(coffee);
    }

    public Coffee update(Long id, CoffeeDto dto) {
        Coffee coffee = dto.toEntity(); // dto를 엔티티로 변환
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (id != coffee.getId() || target == null) {
            return null;
        }

        target.patch(coffee);   // 기존 데이터에 새 데이터 붙이기(일부 수정)
        return coffeeRepository.save(target);
    }

    public Coffee delete(Long id) {
        Coffee target = coffeeRepository.findById(id).orElse(null);

        if (target == null) {
            return null;
        }

        coffeeRepository.delete(target);
        return target;
    }
}
