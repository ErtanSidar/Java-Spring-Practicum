package com.essoft.JavaSpringPracticum.business.requests.productRequests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {

    private String productName;

    private double price;

    private LocalDate expirationDate;
}
