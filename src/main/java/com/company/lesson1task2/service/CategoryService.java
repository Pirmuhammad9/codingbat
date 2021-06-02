package com.company.lesson1task2.service;

import com.company.lesson1task2.entity.Category;
import com.company.lesson1task2.entity.Users;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * This function is used to get all addresses in repository
     *
     * @return ResponseEntity
     */
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    /**
     * This function is used to get address by id
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id) {
        Optional<Category> byId = categoryRepository.findById(id);
        return byId.isPresent() ? new ApiResponse("found", true) : new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     *
     * @param category
     * @return ResponseEntity
     */
    public ApiResponse addCategory(Category category) {
        boolean b = categoryRepository.existsByName(category.getName());
        if (b) {
            return new ApiResponse("this category already exists in database", false);
        }
        Category category1 = new Category();
        category1.setDescription(category.getDescription());
        category1.setName(category.getName());
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     *
     * @param category
     * @return ResponseEntity
     */
    public ApiResponse editCategory(Integer id, Category category) {
        boolean b = categoryRepository.existsByNameAndIdNot(category.getName(), id);
        if (b) {
            return new ApiResponse("this category already exists in database", false);
        }
        Category category1 = new Category();
        category1.setDescription(category.getDescription());
        category1.setName(category.getName());
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     *
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteCategory(Integer id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
