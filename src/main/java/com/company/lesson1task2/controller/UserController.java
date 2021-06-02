package com.company.lesson1task2.controller;

import com.company.lesson1task2.entity.Users;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(userService.getAll());
    }

    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getOne(@PathVariable Integer id){
        ApiResponse response = userService.getOne(id);
        return ResponseEntity.status(response.isSuccess()?200:209).body(response);
    }

    /**
     * This function is used to add new address into database
     * @param users
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> addAddress(@RequestBody Users users){
        ApiResponse apiResponse = userService.addUsers(users);
        return ResponseEntity.status(apiResponse.isSuccess()?201:209).body(apiResponse);
    }

    /**
     * This function is used to edit address by id;
     * @param users
     * @return ResponseEntity
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> editUsers(@PathVariable Integer id, @RequestBody Users users){
        ApiResponse apiResponse = userService.editUsers(id, users);
        return ResponseEntity.status(apiResponse.isSuccess()?202:209).body(apiResponse);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsers(@PathVariable Integer id){
        ApiResponse apiResponse = userService.deleteUsers(id);
        return ResponseEntity.status(apiResponse.isSuccess()?200:209).body(apiResponse);
    }


}
