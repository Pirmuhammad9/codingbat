package com.company.lesson1task2.repository;

import com.company.lesson1task2.entity.Example;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExampleRepository extends JpaRepository<Example, Integer> {
}
