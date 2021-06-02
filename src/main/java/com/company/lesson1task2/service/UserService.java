package com.company.lesson1task2.service;

import com.company.lesson1task2.entity.Users;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UsersRepository usersRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Users> getAll(){
        return usersRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Users> byId = usersRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param users
     * @return ResponseEntity
     */
    public ApiResponse addUsers(Users users){
        boolean b = usersRepository.existsByEmailAndPassword(users.getEmail(), users.getPassword());
        if (b){
            return new ApiResponse("this user already exists in database", false);
        }
        Users users1 = new Users();
        users1.setEmail(users.getEmail());
        users1.setPassword(users.getPassword());
        usersRepository.save(users);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param users
     * @return ResponseEntity
     */
    public ApiResponse editUsers(Integer id, Users users){
        boolean b = usersRepository.existsByEmailAndPasswordAndIdNot(users.getEmail(), users.getPassword(), id);
        if (b){
            return new ApiResponse("this user already exists in database", false);
        }
        if (!usersRepository.findById(id).isPresent()){
            return new ApiResponse("not found", false);
        }
        Users users1 = usersRepository.findById(id).get();
        users1.setEmail(users.getEmail());
        users1.setPassword(users.getPassword());
        usersRepository.save(users);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteUsers(Integer id){
        if (usersRepository.existsById(id)){
            usersRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
