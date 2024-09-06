package com.youyk.graphqlplayground.lec05.controller;

import com.youyk.graphqlplayground.lec05.dto.Address;
import com.youyk.graphqlplayground.lec05.dto.Customer;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class AddressController {

    @SchemaMapping(typeName = "Customer")
    public Mono<Address> address(Customer customer, /*DataFetchingFieldSelectionSet selectionSet*/
                                 DataFetchingEnvironment env){
        System.out.println(
                "address : " + env.getDocument()
        );
        return Mono.just(
          Address.create(customer.getName()+" street", customer.getName()+" city")
        );
    }

}
