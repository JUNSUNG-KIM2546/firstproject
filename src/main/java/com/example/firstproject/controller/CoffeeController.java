package com.example.firstproject.controller;

import com.example.firstproject.dto.CoffeeDto;
import com.example.firstproject.entity.Coffee;
import com.example.firstproject.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    @Autowired
    CoffeeRepository coffeeRepository;

    @GetMapping("/")
    String index(Model model){
        ArrayList<Coffee> coffeeList = coffeeRepository.findAll();
        model.addAttribute("coffeeList", coffeeList);
        return "coffee/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model) {    // 매개변수로 id 받아 오기
        log.info("id = " + id);

        //Optional<It> itEntity = itRepository.findById(id);  // 자바8버전 이후 사용 가능 (밑에와 같은 기능)
        Coffee cfeEntity = coffeeRepository.findById(id).orElse(null);

        model.addAttribute("coffeeShow", cfeEntity);

        return "coffee/show";
    }

    @GetMapping("/new")
    String sign(){
        return "coffee/new";
    }
    @PostMapping ("/new")
    String sign(Model model, CoffeeDto dto){
        log.info(dto.toString());
        //System.out.println(dto.toString());

        // DTO를 엔티티로 변환
        Coffee coffee = dto.toEntity();
        log.info(coffee.toString());
        //System.out.println(it.toString());

        // 리파지터리로 엔티티를 DB에 저장
        Coffee saved = coffeeRepository.save(coffee);   // coffee엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/coffee/";
    }
}
