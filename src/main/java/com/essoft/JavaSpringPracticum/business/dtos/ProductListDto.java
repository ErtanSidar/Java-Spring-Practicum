package com.essoft.JavaSpringPracticum.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductListDto {

    private long id;

    private String productName;

    private double price;

    private LocalDate expirationDate;
}
