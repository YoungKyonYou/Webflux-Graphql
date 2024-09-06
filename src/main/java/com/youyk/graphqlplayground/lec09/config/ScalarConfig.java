package com.youyk.graphqlplayground.lec09.config;

import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class ScalarConfig {

    /**
     * 이 코드는 GraphQL 스키마에 사용될 스칼라 타입들을 정의하는 빈을 설정하는 것입니다.
     * GraphQL에서 스칼라 타입은 가장 기본적인 데이터 타입을 의미합니다.
     * 예를 들어, Int, Float, String, Boolean, ID 등이 기본적으로 제공되는 스칼라 타입입니다.
     * 하지만 이러한 기본 스칼라 타입 외에도 사용자가 직접 정의한 커스텀 스칼라 타입을 사용할 수 있습니다.
     * 이를 통해 특정 언어의 데이터 타입이나 특수한 형식의 데이터를 처리할 수 있습니다.
     * 이 코드에서는 ExtendedScalars 클래스를 통해 다양한 추가적인 스칼라 타입들을 GraphQL 스키마에 등록하고 있습니다.
     * 이렇게 등록된 스칼라 타입들은 GraphQL 스키마에서 사용될 수 있게 됩니다.
     * 따라서 이 빈을 설정하는 이유는 GraphQL 스키마에서 사용할 수 있는 스칼라 타입의 범위를 확장하기 위함입니다.
     * @return
     */
    @Bean
    public RuntimeWiringConfigurer configurer(){
        return c -> c
                .scalar(ExtendedScalars.GraphQLLong)
                .scalar(ExtendedScalars.GraphQLByte)
                .scalar(ExtendedScalars.GraphQLShort)
                .scalar(ExtendedScalars.GraphQLBigDecimal)
                .scalar(ExtendedScalars.GraphQLBigInteger)
                .scalar(ExtendedScalars.Date)
                .scalar(ExtendedScalars.LocalTime)
                .scalar(ExtendedScalars.DateTime)
                .scalar(ExtendedScalars.Object);
    }

}
