package com.example.firstproject.repository;

import com.example.firstproject.entity.Mem;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface MemRepository extends CrudRepository<Mem, Long> {
    @Override
    ArrayList<Mem> findAll();
}
