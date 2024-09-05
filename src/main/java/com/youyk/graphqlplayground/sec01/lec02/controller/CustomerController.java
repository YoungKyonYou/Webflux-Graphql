package com.youyk.graphqlplayground.sec01.lec02.controller;

import com.youyk.graphqlplayground.sec01.lec02.dto.AgeRangeFilter;
import com.youyk.graphqlplayground.sec01.lec02.dto.Customer;
import com.youyk.graphqlplayground.sec01.lec02.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Controller
public class CustomerController {
    private final CustomerService service;

    @QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument("id") Integer id){
        return this.service.customerById(id);
    }

    @QueryMapping
    public Flux<Customer> customersNameContains(@Argument("name") String name){
        return this.service.nameContains(name);
    }

    @QueryMapping
    public Flux<Customer> customersByAgeRange(@Argument("filter") AgeRangeFilter filter){
        return this.service.withinAge(filter);
    }

}
