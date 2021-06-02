package com.company.lesson1task2.controller;

import com.company.lesson1task2.entity.Category;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = categoryService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:209).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param category
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Category category){
        ApiResponse apiResponse = categoryService.addCategory(category);
        return ResponseEntity.status(apiResponse.isSuccess()?201:209).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param category
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editCategory(@PathVariable Integer id, @RequestBody Category category){
        ApiResponse apiResponse = categoryService.editCategory(id, category);
        return ResponseEntity.status(apiResponse.isSuccess()?202:209).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Integer id){
        ApiResponse apiResponse = categoryService.deleteCategory(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:209).body(apiResponse);
    }

}
