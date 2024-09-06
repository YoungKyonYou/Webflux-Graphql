package com.youyk.graphqlplayground.lec11;

import com.youyk.graphqlplayground.lec11.dto.Book;
import com.youyk.graphqlplayground.lec11.dto.Electronics;
import com.youyk.graphqlplayground.lec11.dto.FruitDto;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class SearchEngineController {




    List<Object> list = List.of(
            FruitDto.create("banana", LocalDate.now().plusDays(3)),
            FruitDto.create("apple", LocalDate.now().plusDays(5)),
            Electronics.create("mac book", 600, "APPLE"),
            Electronics.create("phone", 400, "SAMSUNG"),
            Book.create("java", "venkat")
    );

    @QueryMapping
    public Flux<Object> search(){
        return Mono.fromSupplier(() -> new ArrayList<>(list))
                //.doOnNext(Collections::shuffle): doOnNext 메서드는 스트림의 각 아이템에 대해 주어진 작업을 실행합니다.
                // 여기서는 Collections::shuffle 메서드를 사용하여 리스트를 섞습니다.
                .doOnNext(Collections::shuffle)
                //flatMapIterable 메서드는 스트림의 각 아이템을 Iterable로 변환하고, 그 결과를 하나의 Flux로 합칩니다.
                // 여기서는 Function.identity()를 사용하여 아이템 자체를 Iterable로 사용합니다.
                //이 코드에서 flatMapIterable는 Mono 스트림의 단일 아이템 (이 경우에는 ArrayList)을 Flux 스트림으로 변환합니다.
                // 이렇게 하면 ArrayList의 각 요소를 개별적으로 처리할 수 있습니다.
                //즉 Mono를 Flux로 변환하기 위해서 씀
                .flatMapIterable(Function.identity())
                .take(ThreadLocalRandom.current().nextInt(0, list.size()));
    }

}
