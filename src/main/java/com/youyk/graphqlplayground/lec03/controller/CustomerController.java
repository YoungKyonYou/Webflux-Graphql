package com.youyk.graphqlplayground.lec03.controller;

import com.youyk.graphqlplayground.lec03.dto.Customer;
import com.youyk.graphqlplayground.lec03.dto.CustomerOrderDto;
import com.youyk.graphqlplayground.lec03.service.CustomerService;
import com.youyk.graphqlplayground.lec03.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Controller
public class CustomerController {

    private final CustomerService service;
    private final OrderService orderService;

    @SchemaMapping(typeName = "Query")
   // @QueryMapping
    public Flux<Customer> customers(){
        return this.service.allCustomers();
    }

    /**
     * SchemaMapping(typeName = "Customer") 어노테이션은 Spring GraphQL에서 사용되며,
     * 해당 어노테이션을 사용하여 메소드가 GraphQL 스키마의 특정 타입에 매핑되도록 지정할 수 있습니다.
     * 여기서 typeName = "Customer"는 이 메소드가 GraphQL 스키마의 "Customer" 타입에 매핑되어야 함을 나타냅니다. 이는
     * GraphQL 쿼리에서 "Customer" 타입이 요청될 때 이 메소드가 호출되어야 함을 의미합니다.
     */
    @SchemaMapping(typeName = "Customer")
    public Flux<CustomerOrderDto> orders(/*parent object*/Customer customer, @Argument("limit") Integer limit){
        System.out.println("Orders method invoked for " + customer.getName());
        return this.orderService.ordersByCustomerName(customer.getName())
                .take(limit);
    }
}
