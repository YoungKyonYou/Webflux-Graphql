package com.youyk.graphqlplayground.lec08;

import graphql.schema.DataFetchingFieldSelectionSet;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FieldGlobPatternController {

   @QueryMapping
   public Object level1(DataFetchingFieldSelectionSet selectionSet){

       System.out.println(selectionSet.contains("level2"));
       System.out.println(selectionSet.contains("level2/level3"));
       //계층에 level3가 있는지 확인
       System.out.println(selectionSet.contains("**/level3"));
       //level2와 level5 사이에 뭐가 있는지 확인
       System.out.println(selectionSet.contains("level2/**/level5"));

       return null;
   }

}
