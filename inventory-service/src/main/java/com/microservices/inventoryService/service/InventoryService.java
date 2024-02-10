package com.microservices.inventoryService.service;

import com.microservices.inventoryService.entity.Inventory;
import com.microservices.inventoryService.model.InventoryResponse;
import com.microservices.inventoryService.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInventoryAvailable(List<String> skuCodes) {

        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes);

        return inventoryList.stream().map(inventory -> mapInventory(inventory)).collect(Collectors.toList());
    }

    private InventoryResponse mapInventory(Inventory inventory) {

        return InventoryResponse.builder().skuCode(inventory.getSkuCode()).isInventoryAvailable(inventory.getQuantity()>0).build();
    }
}
