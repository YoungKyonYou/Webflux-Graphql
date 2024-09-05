package com.youyk.graphqlplayground.sec01.lec04.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 필드를 schema.graphqls에 정의한대로 꼭 매칭할 필요는 없다
 */
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
