package com.example.firstproject.repository;

import com.example.firstproject.entity.It;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ItRepository extends CrudRepository<It, Long> {
    @Override
    ArrayList<It> findAll();    // Iterable => ArrayList 변경
}
