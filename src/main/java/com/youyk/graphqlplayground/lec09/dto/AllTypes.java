package com.youyk.graphqlplayground.lec09.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "create")
@NoArgsConstructor
public class AllTypes {
    private UUID id;
    private Integer height;
    private Float temperature;
    private String city;
    private Boolean isValid;

    private Long distance;
    private Byte ageInYears; // -128 to 127
    private Short ageInMonths;
    private BigDecimal bigDecimal;
    private BigInteger bigInteger;
    private LocalDate date;
    private LocalTime time;
    private OffsetDateTime dateTime;
    private Car car;
}

/*
type AllTypes{
    id: ID
    height: Int
    temperature: Float
    city: String
    isValid: Boolean
    distance: Long
    ageInYears: Byte
    ageInMonths: Short
    bigDecimal: BigDecimal
    bigInteger: BigInteger
    date: Date
    time: LocalTime
    dateTime: DateTime
    car: Car
}
 */