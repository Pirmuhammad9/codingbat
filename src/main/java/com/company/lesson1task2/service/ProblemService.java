package com.company.lesson1task2.service;

import com.company.lesson1task2.entity.Problem;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.payload.ProblemDto;
import com.company.lesson1task2.repository.LanguageRepository;
import com.company.lesson1task2.repository.ProblemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProblemService {
    @Autowired
    ProblemRepository problemRepository;

    @Autowired
    LanguageRepository languageRepository;

    /**
     * This function is used to get all addresses in repository
     *
     * @return ResponseEntity
     */
    public List<Problem> getAll() {
        return problemRepository.findAll();
    }

    /**
     * This function is used to get address by id
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id) {
        Optional<Problem> byId = problemRepository.findById(id);
        return byId.isPresent() ? new ApiResponse("found", true) : new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     *
     * @param problemDto
     * @return ResponseEntity
     */
    public ApiResponse addProblem(ProblemDto problemDto) {
        boolean b = languageRepository.existsById(problemDto.getLanguageID());
        boolean b1 = problemRepository.existsByName(problemDto.getName());
        boolean b2 = problemRepository.existsByText(problemDto.getText());
        boolean b3 = problemRepository.existsBySolution(problemDto.getSolution());
        if (!b || b1 || b2 || b3) {
            return new ApiResponse("try again", false);
        }
        Problem problem = new Problem();
        problem.setHint(problemDto.getHint());
        problem.setHasStar(problemDto.isHasStar());
        problem.setName(problemDto.getName());
        problem.setLanguage(languageRepository.findById(problemDto.getLanguageID()).get());
        problem.setMethod(problemDto.getMethod());
        problem.setSolution(problemDto.getSolution());
        problem.setText(problemDto.getText());
        problemRepository.save(problem);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     *
     * @param problemDto
     * @return ResponseEntity
     */
    public ApiResponse editProblem(Integer id, ProblemDto problemDto) {
        boolean b = languageRepository.existsById(problemDto.getLanguageID());
        boolean b1 = problemRepository.existsByName(problemDto.getName());
        boolean b2 = problemRepository.existsByText(problemDto.getText());
        boolean b3 = problemRepository.existsBySolution(problemDto.getSolution());
        boolean b4 = problemRepository.existsById(id);
        if (!b || b1 || b2 || b3 || !b4) {
            return new ApiResponse("try again", false);
        }
        Problem problem = problemRepository.findById(id).get();
        problem.setHint(problemDto.getHint());
        problem.setHasStar(problemDto.isHasStar());
        problem.setName(problemDto.getName());
        problem.setLanguage(languageRepository.findById(problemDto.getLanguageID()).get());
        problem.setMethod(problemDto.getMethod());
        problem.setSolution(problemDto.getSolution());
        problem.setText(problemDto.getText());
        problemRepository.save(problem);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     *
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteProblem(Integer id) {
        if (problemRepository.existsById(id)) {
            problemRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
