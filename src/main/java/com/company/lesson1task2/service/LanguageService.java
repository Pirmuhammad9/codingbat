package com.company.lesson1task2.service;

import com.company.lesson1task2.entity.Category;
import com.company.lesson1task2.entity.Language;
import com.company.lesson1task2.payload.ApiResponse;
import com.company.lesson1task2.payload.LanguageDto;
import com.company.lesson1task2.repository.CategoryRepository;
import com.company.lesson1task2.repository.LanguageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    CategoryRepository categoryRepository;


    /**
     * This function is used to get all addresses in repository
     *
     * @return ResponseEntity
     */
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    /**
     * This function is used to get address by id
     *
     * @param id
     * @return ApiResponse
     */
    public ApiResponse getOne(Integer id) {
        Optional<Language> byId = languageRepository.findById(id);
        return byId.isPresent() ? new ApiResponse("found", true) : new ApiResponse("not found", false);
    }

    /**
     * This function is used to add new address into database
     *
     * @param languageDto
     * @return ResponseEntity
     */
    public ApiResponse addLanguage(LanguageDto languageDto) {
        boolean b = languageRepository.existsByName(languageDto.getName());
        if (b) {
            return new ApiResponse("this language already exists in database", false);
        }
        Language language = new Language();
        List<Category> allById = categoryRepository.findAllById(languageDto.getCategoryList());
        language.setName(languageDto.getName());
        language.setCategoryList(allById);
        languageRepository.save(language);
        return new ApiResponse("added", true);
    }

    /**
     * This function is used to edit address by id;
     *
     * @param languageDto
     * @return ResponseEntity
     */
    public ApiResponse editLanguage(Integer id, LanguageDto languageDto) {
        boolean b = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        boolean b1 = languageRepository.existsById(id);
        if (b || !b1) {
            return new ApiResponse("try again!!!", false);
        }

        Language language = languageRepository.findById(id).get();
        List<Category> allById = categoryRepository.findAllById(languageDto.getCategoryList());
        language.setName(languageDto.getName());
        language.setCategoryList(allById);
        languageRepository.save(language);
        return new ApiResponse("edited", true);
    }

    /**
     * This function is aimed to delete address by id
     *
     * @param id
     * @return ResponseEntity
     */
    public ApiResponse deleteLanguage(Integer id) {
        if (languageRepository.existsById(id)) {
            languageRepository.deleteById(id);
            return new ApiResponse("deleted", true);
        }

        return new ApiResponse("not found", false);
    }
}
