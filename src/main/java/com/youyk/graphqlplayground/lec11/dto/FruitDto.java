package com.youyk.graphqlplayground.lec11.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 이런 경우 schemal.graphqls와 클래스명이 같아야된다!
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class FruitDto {

    private String description;
    private LocalDate expiryDate;

}
