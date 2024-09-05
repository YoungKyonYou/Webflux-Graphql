package com.youyk.graphqlplayground.sec01.lec05.controller;

import com.youyk.graphqlplayground.sec01.lec05.dto.Address;
import com.youyk.graphqlplayground.sec01.lec05.dto.Customer;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer){
        return Mono.just(
          Address.create(customer.getName()+" street", customer.getName()+" city")
        );
    }

}
