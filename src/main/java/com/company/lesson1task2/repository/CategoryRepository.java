package com.company.lesson1task2.repository;

import com.company.lesson1task2.entity.Answer;
import com.company.lesson1task2.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
