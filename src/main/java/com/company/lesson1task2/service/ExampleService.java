package com.company.lesson1task2.service;


import com.company.lesson1task2.entity.Example;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.payload.ExampleDto;
import com.company.lesson1task2.repository.ExampleRepository;
import com.company.lesson1task2.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    ExampleRepository exampleRepository;

    @Autowired
    ProblemRepository problemRepository;

    /**
     * This function is used to get all addresses in repository
     *
     * @return ResponseEntity
     */
    public List<Example> getAll() {
        return exampleRepository.findAll();
    }

    /**
     * This function is used to get address by id
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id) {
        Optional<Example> byId = exampleRepository.findById(id);
        return byId.isPresent() ? new ApiResponse("found", true) : new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     *
     * @param exampleDto
     * @return ResponseEntity
     */
    public ApiResponse addExample(ExampleDto exampleDto) {
        boolean b = problemRepository.existsById(exampleDto.getProblemID());
        if (!b) {
            return new ApiResponse("try again", false);
        }
        Example example = new Example();
        example.setProblem(problemRepository.findById(exampleDto.getProblemID()).get());
        example.setText(example.getText());
        exampleRepository.save(example);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     *
     * @param exampleDto
     * @return ResponseEntity
     */
    public ApiResponse editExample(Integer id, ExampleDto exampleDto) {
        boolean b = problemRepository.existsById(exampleDto.getProblemID());
        boolean b1 = exampleRepository.existsById(id);
        if (!b || !b1) {
            return new ApiResponse("try again", false);
        }
        Example example = exampleRepository.findById(id).get();
        example.setProblem(problemRepository.findById(exampleDto.getProblemID()).get());
        example.setText(example.getText());
        exampleRepository.save(example);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     *
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteExample(Integer id) {
        if (exampleRepository.existsById(id)) {
            exampleRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
