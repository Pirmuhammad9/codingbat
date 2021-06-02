package com.company.lesson1task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDto {
    private String name;
    private List<Integer> categoryList;
}
