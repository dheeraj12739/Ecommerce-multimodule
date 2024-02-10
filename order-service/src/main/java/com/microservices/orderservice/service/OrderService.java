package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderLineItemRequest;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.entity.Order;
import com.microservices.orderservice.entity.OrderLineItem;
import com.microservices.orderservice.repository.OrderLineItemRepository;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderLineItemRepository orderLineItemRepository;

    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(orderRequest.getOrderNumber());
        List<OrderLineItem> orderLineItemList = new ArrayList<>();
        for (OrderLineItemRequest orderLineItemRequest : orderRequest.getOrderLineItems()) {

            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setPrice(orderLineItemRequest.getPrice());
            orderLineItem.setQuantity(orderLineItemRequest.getQuantity());
            orderLineItem.setSkuCode(orderLineItemRequest.getSkuCode());
            orderLineItemList.add(orderLineItem);
        }
        order.setOrderLineItems(orderLineItemList);

        List<String> skuCodes = orderLineItemList.stream().map(this::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri("http://localhost:3005/api/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class).block();

        boolean allInventoryAvailable = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInventoryAvailable);

        if (allInventoryAvailable && inventoryResponses.length > 0) {
            orderRepository.save(order);
        }else {

            throw new IllegalArgumentException("Product not in stock try again later");
        }
    }

    private String getSkuCode(OrderLineItem orderLineItem) {
        return orderLineItem.getSkuCode();
    }
}
