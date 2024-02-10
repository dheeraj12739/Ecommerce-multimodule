package com.microservices.orderservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter

public class OrderRequest {

    private String orderNumber;

    private List<OrderLineItemRequest> orderLineItems;
}
