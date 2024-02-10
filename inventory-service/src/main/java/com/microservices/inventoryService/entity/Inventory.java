package com.microservices.inventoryService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue
    private Long id;

    private String skuCode;

    private Long quantity;

}
