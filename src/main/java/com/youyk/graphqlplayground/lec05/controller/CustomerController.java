package com.youyk.graphqlplayground.lec05.controller;

import com.youyk.graphqlplayground.lec05.dto.Customer;
import com.youyk.graphqlplayground.lec05.service.CustomerService;
import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService service;

    @QueryMapping
   // @QueryMapping
    public Flux<Customer> customers(/*DataFetchingFieldSelectionSet selectionSet*/ DataFetchingEnvironment env){
        System.out.println(
                "customer : " + env.getDocument()
        );
        return this.service.allCustomers();
    }


}
