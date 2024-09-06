package com.youyk.graphqlplayground.lec05.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class Account {
    private UUID id;
    private Integer amount;
    private String accountType;

}
