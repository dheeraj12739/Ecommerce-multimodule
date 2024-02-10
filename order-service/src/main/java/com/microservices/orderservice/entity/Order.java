package com.microservices.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders") // Use a different table name
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL) // Specify target entity and cascade type
    private List<OrderLineItem> orderLineItems;
}
