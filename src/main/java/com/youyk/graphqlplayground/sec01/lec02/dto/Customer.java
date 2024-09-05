package com.youyk.graphqlplayground.sec01.lec02.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//static 메서도로 만들고 모든 필드 값을 매개변수로 받는 생성자 생성함 메서드명은 create
@AllArgsConstructor(staticName ="create")
public class Customer {
    private Integer id;
    private String name;
    private Integer age;
    private String city;
}
