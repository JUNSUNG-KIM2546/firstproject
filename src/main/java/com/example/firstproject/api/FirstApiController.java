package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // REST API용 컨트롤러
@RequestMapping("/api")
public class FirstApiController {
    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }
}
