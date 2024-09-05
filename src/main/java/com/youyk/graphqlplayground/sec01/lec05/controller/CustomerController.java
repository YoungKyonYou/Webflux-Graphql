package com.youyk.graphqlplayground.sec01.lec05.controller;

import com.youyk.graphqlplayground.sec01.lec05.dto.Customer;
import com.youyk.graphqlplayground.sec01.lec05.service.CustomerService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService service;

    @SchemaMapping(typeName = "Query")
   // @QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }


}
