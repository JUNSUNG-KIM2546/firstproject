package com.example.firstproject.service;

import com.example.firstproject.entity.Mem;
import com.example.firstproject.repository.MemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemService {
    @Autowired
    MemRepository memRepository;

    public List<Mem> memList() {
        return memRepository.findAll();
    }

    public Mem memSelect(Long id) {
        return memRepository.findById(id).orElse(null);
    }
}
