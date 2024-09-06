package com.youyk.graphqlplayground.lec05.controller;

import com.youyk.graphqlplayground.lec05.dto.Account;
import com.youyk.graphqlplayground.lec05.dto.Customer;
import graphql.schema.DataFetchingEnvironment;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AccountController {
    /**
     * @SchemaMapping 어노테이션은 메소드가 GraphQL
     * 스키마의 특정 타입에 매핑되도록 지정하는 데 사용됩니다.
     * typeName 속성을 명시적으로 제공하지 않으면,
     * 메소드 이름이 스키마의 필드 이름으로 사용됩니다.
     */
    @SchemaMapping
    public Mono<Account> account(Customer customer, /*DataFetchingFieldSelectionSet selectionSet*/
                                 DataFetchingEnvironment env){
        System.out.println(
                "account : " + env.getDocument()
        );
        String type = ThreadLocalRandom.current().nextBoolean() ? "CHECKING" : "SAVING";

        return Mono.just(
                Account.create(
                        UUID.randomUUID(),
                        ThreadLocalRandom.current().nextInt(1, 1000),
                        type
                )
        );
    }
}
