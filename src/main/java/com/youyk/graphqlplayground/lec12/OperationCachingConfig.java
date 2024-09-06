package com.youyk.graphqlplayground.lec12;

import graphql.ExecutionInput;
import graphql.execution.preparsed.PreparsedDocumentEntry;
import graphql.execution.preparsed.PreparsedDocumentProvider;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OperationCachingConfig {
    /*
        request body
        exe doc
        parse
        validation
        exe

        suggestion:
        use variables along with operation name
        계속 이렇게 map에 캐싱하다보면 overflow날 수 있으니 ben-manes/caffeine 캐시를 쓸 것 (깃허브 참고)
        prefer this: https://github.com/ben-manes/caffeine
     */

    @Bean
    public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer(PreparsedDocumentProvider provider){
        return c -> c.configureGraphQl(builder -> builder.preparsedDocumentProvider(provider));
    }

    //아래 코드는 매번 graphQL 요청마다 호출된다.
    @Bean
    public PreparsedDocumentProvider provider(){
        Map<String, PreparsedDocumentEntry> map = new ConcurrentHashMap<>();
        return (executionInput, parseAndValidateFunction) -> {
            //computeIfAbsent은 Java의 Map 인터페이스에서 제공하는 메서드입니다. 이 메서드는 주어진 키에 대한 값이 맵에 없을 경우, 주어진 함수를 사용하여 새 값을 계산하고 이를 맵에 저장합니다.
            PreparsedDocumentEntry documentEntry = map.computeIfAbsent(executionInput.getQuery(), key -> {
                //주어진 key 값이 없으면 실행
                System.out.println("Not found : " + key);
                PreparsedDocumentEntry r = parseAndValidateFunction.apply(executionInput);
                System.out.println("Caching : " + r);
                return r;
            });

            /**
             * documentEntry 값을 결과로 가지는 CompletableFuture를 생성하고 반환합니다.
             * 이 CompletableFuture는 이미 완료된 상태이므로, 이 CompletableFuture의 결과를 가져오는 메서드를 호출하면
             * 즉시 documentEntry 값을 반환합니다.
             */
            return CompletableFuture.completedFuture(documentEntry);
        };
    }
}
