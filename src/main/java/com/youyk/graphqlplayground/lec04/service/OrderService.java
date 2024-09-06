package com.youyk.graphqlplayground.lec04.service;

import com.youyk.graphqlplayground.lec04.dto.Customer;
import com.youyk.graphqlplayground.lec04.dto.CustomerOrderDto;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

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

    /**
     * [SIZE PROBLEM] 여기서 문제가 발생한다.
     * The size of the promised values MUST be the same size as the key list 이런 에러가 발생하는데 무슨 의미냐면
     * List<String> names 파라미터로 4개의 아이템을 주는데
     * 왜 2개만 주냐는 것이다
     * 그래서  .flatMap(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
     * 이렇게 해결했다
     */
    public Flux<List<CustomerOrderDto>> ordersByCustomerName(List<String> names){
        return Flux.fromIterable(names)
               // .flatMap(this::fetchOrders);
                //.flatMap(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
                .flatMapSequential(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));
    }


    /**
     * [ORDER PROBLEM] 여기서 문제가 발생한다.
     * .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 500))); 를 붙이고 나니까
     * 순서가 뒤죽박죽이 되었다. order가 뒤죽박죽이 되는 것을 확인할 수 있다.
     * 이유는 ordersByCustomerName 메서드에서 flatMap은 parallel하게 동작하기 때문이다.
     * flatMap를 사용할 때는 정말 주의해야 한다. ( .flatMap(n -> fetchOrders(n).defaultIfEmpty(Collections.emptyList()));)
     * 그래서 flatMapSequential를 사용하면 순서가 보장된다.
     */
    // some source
    private Mono<List<CustomerOrderDto>> fetchOrders(String name){
        return Mono.justOrEmpty(map.get(name))
                .delayElement(Duration.ofMillis(ThreadLocalRandom.current().nextInt(0, 500)));
    }

    public Mono<Map<Customer, List<CustomerOrderDto>>> fetchOrderAsMap(List<Customer> customers){
        return Flux.fromIterable(customers)
                .map(c -> Tuples.of(c, map.getOrDefault(c.getName(), Collections.emptyList())))
                .collectMap(Tuple2::getT1, Tuple2::getT2);
    }
}
