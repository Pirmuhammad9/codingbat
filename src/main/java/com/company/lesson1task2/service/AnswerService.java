package com.company.lesson1task2.service;

import com.company.lesson1task2.entity.Answer;
import com.company.lesson1task2.entity.Users;
import com.company.lesson1task2.payload.AnswerDto;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.repository.AnswerRepository;
import com.company.lesson1task2.repository.ProblemRepository;
import com.company.lesson1task2.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    ProblemRepository problemRepository;
    @Autowired
    UsersRepository usersRepository;

    /**
     * This function is used to get all addresses in repository
     * @return ResponseEntity
     */
    public List<Answer> getAll(){
        return answerRepository.findAll();
    }
    /**
     * This function is used to get address by id
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id){
        Optional<Answer> byId = answerRepository.findById(id);
        return byId.isPresent()?new ApiResponse("found", true):new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     * @param answerDto
     * @return ResponseEntity
     */
    public ApiResponse addAnswer(AnswerDto answerDto){
        boolean b = problemRepository.existsById(answerDto.getProblemID());
        boolean b1 = usersRepository.existsById(answerDto.getUsersID());
        if (!b || !b1){
            return new ApiResponse("try again", false);
        }
        Answer answer = new Answer();
        answer.setProblem(problemRepository.findById(answerDto.getProblemID()).get());
        answer.setText(answerDto.getText());
        answer.setTrue(answer.isTrue());
        answer.setUsers(usersRepository.findById(answerDto.getUsersID()).get());
        answerRepository.save(answer);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     * @param answerDto
     * @return ResponseEntity
     */
    public ApiResponse editAnswers(Integer id, AnswerDto answerDto){
        boolean b = problemRepository.existsById(answerDto.getProblemID());
        boolean b1 = usersRepository.existsById(answerDto.getUsersID());
        boolean b2 = answerRepository.existsById(id);
        if (!b || !b1 || !b2){
            return new ApiResponse("try again", false);
        }
        Answer answer = answerRepository.findById(id).get();
        answer.setProblem(problemRepository.findById(answerDto.getProblemID()).get());
        answer.setText(answerDto.getText());
        answer.setTrue(answer.isTrue());
        answer.setUsers(usersRepository.findById(answerDto.getUsersID()).get());
        answerRepository.save(answer);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteAnswers(Integer id){
        if (answerRepository.existsById(id)){
            answerRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
