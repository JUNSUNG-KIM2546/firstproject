package com.example.firstproject.api;

import com.example.firstproject.entity.Mem;
import com.example.firstproject.service.MemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // REST API용 컨트롤러
@RequestMapping("/api")
public class MemApiController {
    @Autowired
    MemService memService;

    @GetMapping("/memlist")
    public List<Mem> memList() {
        return memService.memList();
    }
    @GetMapping("/memlist{id}")
    public Mem memList(@PathVariable Long id) {
        return memService.memSelect(id);
    }
}
