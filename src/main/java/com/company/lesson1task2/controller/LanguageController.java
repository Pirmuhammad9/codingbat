package com.company.lesson1task2.controller;

import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.payload.LanguageDto;
import com.company.lesson1task2.service.LanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/languages")
public class LanguageController {
    @Autowired
    LanguageService languageService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(languageService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = languageService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:209).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param languageDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addLanguage(@RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.addLanguage(languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:209).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param languageDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editLanguage(@PathVariable Integer id, @RequestBody LanguageDto languageDto){
        ApiResponse apiResponse = languageService.editLanguage(id, languageDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:209).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLanguage(@PathVariable Integer id){
        ApiResponse apiResponse = languageService.deleteLanguage(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:209).body(apiResponse);
    }

}
