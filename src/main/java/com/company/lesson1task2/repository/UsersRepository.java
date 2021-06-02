package com.company.lesson1task2.repository;

import com.company.lesson1task2.entity.Category;
import com.company.lesson1task2.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    boolean existsByEmailAndPassword(String email, String password);
    boolean existsByEmailAndPasswordAndIdNot(String email, String password, Integer id);
}
