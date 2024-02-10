package com.microservices.inventoryService.controller;

import com.microservices.inventoryService.model.InventoryResponse;
import com.microservices.inventoryService.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<InventoryResponse> isInventoryAvailable(@RequestParam List<String> skuCode) {

        return inventoryService.isInventoryAvailable(skuCode);
    }
}
