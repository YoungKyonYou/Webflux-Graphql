package com.youyk.graphqlplayground.lec06.controller;

import com.youyk.graphqlplayground.lec06.dto.CustomerOrderDto;
import com.youyk.graphqlplayground.lec06.dto.CustomerWithOrder;
import com.youyk.graphqlplayground.lec06.service.CustomerOrderDataFetcher;
import com.youyk.graphqlplayground.lec06.service.CustomerService;
import com.youyk.graphqlplayground.lec06.service.OrderService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
public class CustomerRestController {
    private final CustomerService service;
    private final OrderService orderService;

    @GetMapping("customers")
    public Flux<CustomerWithOrder> customerOrders(){
        return this.service.allCustomers()
                .flatMap(c -> this.orderService.ordersByCustomerName(c.getName())
                        .collectList()
                        .map(l -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(),l))
                );
    }


}
