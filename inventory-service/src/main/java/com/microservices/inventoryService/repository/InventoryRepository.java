package com.microservices.inventoryService.repository;

import com.microservices.inventoryService.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    Optional<Inventory> findByskuCode(String skuCode);
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);

}
