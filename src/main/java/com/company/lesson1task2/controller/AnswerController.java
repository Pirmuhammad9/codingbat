package com.company.lesson1task2.controller;

import com.company.lesson1task2.payload.AnswerDto;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    @Autowired
    AnswerService answerService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(answerService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = answerService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:209).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param answerDto
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addAnswer(@RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.addAnswer(answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:209).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param answerDto
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editUsers(@PathVariable Integer id, @RequestBody AnswerDto answerDto){
        ApiResponse apiResponse = answerService.editAnswers(id, answerDto);
        return ResponseEntity.status(apiResponse.isSuccess()?202:209).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Integer id){
        ApiResponse apiResponse = answerService.deleteAnswers(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:209).body(apiResponse);
    }

}
