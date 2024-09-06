package com.youyk.graphqlplayground.lec06.controller;

import com.youyk.graphqlplayground.lec06.dto.Customer;
import com.youyk.graphqlplayground.lec06.dto.CustomerOrderDto;
import com.youyk.graphqlplayground.lec06.dto.CustomerWithOrder;
import com.youyk.graphqlplayground.lec06.service.CustomerOrderDataFetcher;
import com.youyk.graphqlplayground.lec06.service.CustomerService;
import com.youyk.graphqlplayground.lec06.service.OrderService;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerOrderDataFetcher dataFetcher;

    /**
     * @SchemaMapping(typeName = "Query") 어노테이션은 customers()
     * 메소드가 GraphQL 스키마의 "Query" 타입에 매핑되어야 함을 나타냅니다.
     * 이는 GraphQL 쿼리에서 "Query" 타입이 요청될 때 이 메소드가 호출되어야 함을 의미합니다.
     * "Query"는 GraphQL에서 데이터를 읽는데 사용되는 기본 타입입니다.
     */
    @SchemaMapping(typeName = "Query")
   // @QueryMapping
    public Flux<CustomerWithOrder> customers(DataFetchingFieldSelectionSet selectionSet){
        return this.dataFetcher.customerOrders(selectionSet);
    }

/*    *//**
     * SchemaMapping(typeName = "Customer") 어노테이션은 Spring GraphQL에서 사용되며,
     * 해당 어노테이션을 사용하여 메소드가 GraphQL 스키마의 특정 타입에 매핑되도록 지정할 수 있습니다.
     * 여기서 typeName = "Customer"는 이 메소드가 GraphQL 스키마의 "Customer" 타입에 매핑되어야 함을 나타냅니다. 이는
     * GraphQL 쿼리에서 "Customer" 타입이 요청될 때 이 메소드가 호출되어야 함을 의미합니다.
     *//*
    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrderDto> orders(*//*parent object*//*Customer customer){
        System.out.println("Orders method invoked for " + customer.getName());
        return this.orderService.ordersByCustomerName(customer.getName());
    }*/
}
