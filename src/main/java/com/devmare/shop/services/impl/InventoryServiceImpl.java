package com.devmare.shop.services.impl;

import org.springframework.stereotype.Service;

import com.devmare.shop.models.Inventory;
import com.devmare.shop.repositories.InventoryRepository;
import com.devmare.shop.services.InventoryService;

import lombok.Data;

@Data
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    // This method is used to create an inventory.
    @Override
    public Inventory createInventory(Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    // This method is used to get the inventory.
    @Override
    public Inventory getInventory() {
        return inventoryRepository.findAll().getFirst();
    }
}
