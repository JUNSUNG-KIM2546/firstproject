package com.example.firstproject.controller;

import com.example.firstproject.dto.ItDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.It;
import com.example.firstproject.repository.ItRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j  // 로깅 기능을 위한 어노테이션
@Controller
@RequestMapping("/it")
public class ItController {

    @Autowired
    ItRepository itRepository;

    @GetMapping("/")
    String index(Model model){
        ArrayList<It> itList = itRepository.findAll();
        model.addAttribute("itList", itList);
        return "it/index";
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model) {    // 매개변수로 id 받아 오기
        log.info("id = " + id);

        //Optional<It> itEntity = itRepository.findById(id);  // 자바8버전 이후 사용 가능 (밑에와 같은 기능)
        It itEntity = itRepository.findById(id).orElse(null);

        model.addAttribute("itShow", itEntity);

        return "it/show";
    }

    @GetMapping("/new")
    String sign(){
        return "it/new";
    }
    @PostMapping ("/new")
    String sign(Model model, ItDto dto){
        log.info(dto.toString());
        //System.out.println(dto.toString());

        // DTO를 엔티티로 변환
        It it = dto.toEntity();
        log.info(it.toString());
        //System.out.println(it.toString());

        // 리파지터리로 엔티티를 DB에 저장
        It saved = itRepository.save(it);   // it엔티티를 저장해 saved 객체에 반환
        log.info(saved.toString());
        //System.out.println(saved.toString());
        return "redirect:/it/";
    }
}
