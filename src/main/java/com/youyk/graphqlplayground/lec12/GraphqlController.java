package com.youyk.graphqlplayground.lec12;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
public class GraphqlController {


    //@Argument은 @PathVariable과 비슷한 것
    @QueryMapping
    public Mono<String> sayHello(@Argument("name") String value){
        return Mono.fromSupplier(() -> "Hello " + value);
    }


}
