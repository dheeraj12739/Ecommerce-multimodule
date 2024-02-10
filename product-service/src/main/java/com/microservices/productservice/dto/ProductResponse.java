package com.microservices.productservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long productId;
}
