package com.company.lesson1task2.repository;

import com.company.lesson1task2.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
