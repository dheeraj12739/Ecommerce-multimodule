package com.microservices.productservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Double price;

    private Long productId;

    public Product(String name, String description, Double price, Long productId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.productId = productId;
    }
}
