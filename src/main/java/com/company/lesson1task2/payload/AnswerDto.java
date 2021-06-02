package com.company.lesson1task2.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto {
    private String text;
    private Integer problemID;
    private Integer usersID;
    private boolean isTrue;
}
