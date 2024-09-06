package com.youyk.graphqlplayground.lec07.service;


import com.youyk.graphqlplayground.lec07.dto.CustomerWithOrder;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import graphql.schema.DataFetchingFieldSelectionSet;
import java.util.Collections;
import java.util.function.UnaryOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class CustomerOrderDataFetcher implements DataFetcher<Flux<CustomerWithOrder>> {
    private final CustomerService customerService;
    private final OrderService orderService;

    //이런 식으로 DataFetcher를 구현할 수도 있다.
    @Override
    public Flux<CustomerWithOrder> get(DataFetchingEnvironment environment) throws Exception {
        boolean includeOrders = environment.getSelectionSet().contains("orders");
        System.out.println(includeOrders);
        return this.customerService.allCustomers()
                .map(c -> CustomerWithOrder.create(c.getId(), c.getName(), c.getAge(), c.getCity(),
                        Collections.emptyList()))
                .transform(this.updateOrdersTransformer(includeOrders));
    }


    /**
     * [ORDER PROBLEM] 여기서 문제가 발생한다.
     * this.customerService.allCustomers()ㅇ,; .delayElement 를 붙이고 나니까
     * 순서가 뒤죽박죽이 되었다. order가 뒤죽박죽이 되는 것을 확인할 수 있다.
     * 이유는 ordersByCustomerName 메서드에서 flatMap은 parallel하게 동작하기 때문이다.
     * flatMap를 사용할 때는 정말 주의해야 한다. (     return includeOrders ? f -> f.flatMapSequential(this::fetchOrders) : f-> f;)
     * 그래서 flatMapSequential를 사용하면 순서가 보장된다.
     */
    private UnaryOperator<Flux<CustomerWithOrder>> updateOrdersTransformer(boolean includeOrders){
        return includeOrders ? f -> f.flatMapSequential(this::fetchOrders) : f-> f;
    }

    private Mono<CustomerWithOrder> fetchOrders(CustomerWithOrder customerWithOrder){
        return this.orderService.ordersByCustomerName(customerWithOrder.getName())
                .collectList()
                .doOnNext(customerWithOrder::setOrders)
                .thenReturn(customerWithOrder);
    }



}
