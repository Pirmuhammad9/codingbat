package com.company.lesson1task2.repository;

import com.company.lesson1task2.entity.Category;
import com.company.lesson1task2.entity.Language;
import com.company.lesson1task2.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    boolean existsByName(String name);
    boolean existsByText(String text);
    boolean existsBySolution(String solution);
}
