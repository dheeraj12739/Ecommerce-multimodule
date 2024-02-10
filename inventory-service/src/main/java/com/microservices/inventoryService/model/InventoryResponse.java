package com.microservices.inventoryService.model;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private String skuCode;

    private Boolean isInventoryAvailable;
}
