package com.youyk.graphqlplayground.sec01.lec04.controller;

import com.youyk.graphqlplayground.sec01.lec04.dto.Customer;
import com.youyk.graphqlplayground.sec01.lec04.dto.CustomerOrderDto;
import com.youyk.graphqlplayground.sec01.lec04.service.CustomerService;
import com.youyk.graphqlplayground.sec01.lec04.service.OrderService;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

   //N+1 해결하기 위한 어노테이션
    /*
    @BatchMapping 어노테이션을 사용하면, 한 번에 여러 객체를 가져오는 방식으로 이 문제를 해결할 수 있습니다.
    이 어노테이션은 메소드에 적용되며,
    이 메소드는 부모 객체의 목록을 인수로 받아서 각 부모 객체에 대한 결과를 반환합니다.
     */
/*    @BatchMapping
    public Flux<List<CustomerOrderDto>> orders(*//*parent object*//*List<Customer> list){
        System.out.println("Orders method invoked for " + list);
        return this.orderService.ordersByCustomerName(
                list.stream().map(Customer::getName).toList()
        );
    }*/

    // N+1의 다른 해결책
    @BatchMapping
    public Mono<Map<Customer, List<CustomerOrderDto>>> orders(/*parent object*/List<Customer> list){
        System.out.println("Orders method invoked for " + list);
        return this.orderService.fetchOrderAsMap(list);
    }

    /**
     * 이렇게 field overriding을 할 수 있다.
     * 모든 age가 100으로 세팅된다.
     * 각 필드에 대한 값을 찾을 때 메서드명을 찾아서 매핑하기 떄문
     * age2()로 하면 안됨 age로 하면 그 age 필드에 해당하는 메서드 명을 찾아서 이걸 실행시키고 그게 덮여 씌워짐
     * 위에건 Object 타입이고 이건 age 단일 값이기 때문에 이걸로 더 호출돼서 덮어씌워짐
     */
    @SchemaMapping(typeName = "Customer")
    public Mono<Integer> age(){
        return Mono.just(100);
    }
}
