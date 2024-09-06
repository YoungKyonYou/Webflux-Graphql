package com.youyk.graphqlplayground.lec07.config;

import com.youyk.graphqlplayground.lec07.service.CustomerOrderDataFetcher;
import graphql.schema.DataFetcher;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@RequiredArgsConstructor
@Configuration
public class DataFetcherWiringConfig {
    private final CustomerOrderDataFetcher dataFetcher;


    /**
     *  이 경우, "Query" 타입의 "customers" 필드에 대한 데이터 패처를 설정하고 있습니다.
     *  이 데이터 패처는 dataFetcher라는 이름의 CustomerOrderDataFetcher 타입의 bean을 사용합니다.
     *  따라서, 클라이언트가 "customers" 필드를 요청하면, dataFetcher가 호출되어 필요한 데이터를 가져옵니다.
     *  이렇게 데이터 패처를 설정함으로써, GraphQL 쿼리를 통해 요청받은 필드의 데이터를 효율적으로 가져올 수 있습니다.
     *  이러한 설정은 GraphQL 서버의 핵심적인 부분이며,
     *  이를 통해 클라이언트는 필요한 데이터만을 효율적으로 요청하고 받아올 수 있습니다.
     *
     */
    @Bean
    public RuntimeWiringConfigurer configurer(){
        //type Query은 .graphqls에서 정의한 Query 타입을 의미
        return c -> c.type("Query", b -> b.dataFetcher("customers", dataFetcher));
    }

    private Map<String, DataFetcher> map(){
        return Map.of(
                "customers" , dfe -> "s",
                "age", dfe->12,
                "city", dfe -> "atlanta"
        );
    }

}
