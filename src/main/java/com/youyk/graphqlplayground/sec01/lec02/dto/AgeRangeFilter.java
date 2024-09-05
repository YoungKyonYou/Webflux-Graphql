package com.youyk.graphqlplayground.sec01.lec02.dto;

import lombok.Data;

@Data
public class AgeRangeFilter {
    private Integer minAge;
    private Integer maxAge;
}
