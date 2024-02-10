package com.microservices.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class OrderLineItemRequest {

    private String skuCode;

    private Integer quantity;

    private Double price;

}
