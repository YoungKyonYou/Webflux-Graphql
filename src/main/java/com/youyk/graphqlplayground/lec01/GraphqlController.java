package com.youyk.graphqlplayground.lec01;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {

    @QueryMapping("sayHello")
    public Mono<String> helloWorld(){
        return Mono.just("Hello world")
                .delayElement(Duration.ofMillis(800));
    }

    //@Argument은 @PathVariable과 비슷한 것
    @QueryMapping
    public Mono<String> sayHelloTo(@Argument("name") String value){
        return Mono.fromSupplier(() -> "Hello " + value)
                .delayElement(Duration.ofMillis(900));
    }

    @QueryMapping
    public Mono<Integer> random(){
        return Mono.just(ThreadLocalRandom.current().nextInt(1, 100))
                .delayElement(Duration.ofMillis(1000));
    }
}
