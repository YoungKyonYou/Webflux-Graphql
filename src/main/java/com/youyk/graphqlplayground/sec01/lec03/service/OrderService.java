package com.youyk.graphqlplayground.sec01.lec03.service;

import com.youyk.graphqlplayground.sec01.lec03.dto.CustomerOrderDto;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OrderService {
    private final Map<String, List<CustomerOrderDto>> map = Map.of(
            "sam", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "sam-product-2")
            ),
            "mike", List.of(
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-1"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-2"),
                    CustomerOrderDto.create(UUID.randomUUID(), "mike-product-3")
            )
    );

    public Flux<CustomerOrderDto> ordersByCustomerName(String name){
        return Flux.fromIterable(map.getOrDefault(name, Collections.emptyList()));
    }
}
