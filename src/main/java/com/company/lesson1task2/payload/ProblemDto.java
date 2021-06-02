package com.company.lesson1task2.payload;

import com.company.lesson1task2.entity.Language;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDto {
    private String name;

    private String text;

    private String solution;

    private String hint;

    private String method;

    private boolean hasStar;

    private Integer languageID;
}
