package com.example.firstproject.controller;

import com.example.firstproject.dto.MemDto;
import com.example.firstproject.entity.Mem;
import com.example.firstproject.repository.MemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("mem")
public class MemController {

    @Autowired
    private MemRepository memRepository;  // memRepository 객체 선언
    
    @GetMapping("/signup")
    String signup(Model model){
        return "mem/signup";
    }
    @PostMapping("/signup")
    String signup(MemDto mdto){
        // DTO를 엔티티로 변환
        log.info(mdto.toString());
        Mem mem = mdto.toEntity();

        // 리파지터리로 엔티티를 DB에 저장
        log.info(mem.toString());
        Mem saved = memRepository.save(mem);

        log.info(saved.toString());

        return "redirect:/mem/" + saved.getId();
    }

    @GetMapping("/{id}")
    String show(@PathVariable Long id, Model model){

    Mem memEntity = memRepository.findById(id).orElse(null);    // 스프링에서 서비스를 호출하는거랑 비슷한 부분 ( Repository == Service )

    model.addAttribute("memshow", memEntity);

        return "mem/show";
    }

    @GetMapping("/")
    String index(Model model) {
        List<Mem> memList = memRepository.findAll();    // 스프링에서 서비스를 호출하는거랑 비슷한 부분 ( Repository == Service )
        model.addAttribute("memList", memList);
        return "mem/index";
    }

    @GetMapping("/edit{id}")
    String edit(@PathVariable Long id, Model model) {
        Mem memE = memRepository.findById(id).orElse(null);
        model.addAttribute("memE", memE);
        return "mem/edit";
    }
    @PostMapping ("/edit{id}")
    String edit(MemDto memDto, Model model) {
        // DTO를 엔티티로 변환
        log.info(memDto.toString());
        Mem mem = memDto.toEntity();    // DTO를 엔티티로 변환

        // 리파지터리로 엔티티를 DB에 저장
        log.info(mem.toString());
        if(mem.getId() != null){    // memID가 낫널이면 수정이 가능
            Mem saved = memRepository.save(mem);
            log.info(saved.toString());

            return "redirect:/mem/" + saved.getId();
        }
        else {
            return "redirect:/mem/";
        }
    }
}
