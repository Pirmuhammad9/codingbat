package com.company.lesson1task2.controller;

import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.payload.ExampleDto;
import com.company.lesson1task2.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examples")
public class ExampleController {
    @Autowired
    ExampleService exampleService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(exampleService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = exampleService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:209).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param exampleDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addExample(@RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.addExample(exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:209).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param exampleDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editExample(@PathVariable Integer id, @RequestBody ExampleDto exampleDto){
        ApiResponse apiResponse = exampleService.editExample(id, exampleDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:209).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExample(@PathVariable Integer id){
        ApiResponse apiResponse = exampleService.deleteExample(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:209).body(apiResponse);
    }

}
