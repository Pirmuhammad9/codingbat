package com.company.lesson1task2.payload;

import com.company.lesson1task2.entity.Problem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleDto {
    private String text;
    private Integer problemID;
}
