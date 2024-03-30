package com.devmare.shop.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmare.shop.constants.AppConstant;
import com.devmare.shop.models.Inventory;
import com.devmare.shop.services.InventoryService;

import lombok.Data;

@Data
@RestController
@RequestMapping(AppConstant.BASE_URL)
public class InventoryController {

    private final InventoryService inventoryService;

    /**
     * This method is used to create an inventory.
     *
     * @param inventory
     * @return ResponseEntity<Inventory>
     */
    @PostMapping("inventory")
    public ResponseEntity<Inventory> createInventory(
            @RequestBody Inventory inventory) {
        return new ResponseEntity<>(inventoryService.createInventory(inventory), HttpStatus.CREATED);
    }

    /**
     * This method is used to get the inventory.
     *
     * @return ResponseEntity<Inventory>
     */
    @GetMapping("inventory")
    public ResponseEntity<Inventory> getInventory() {
        Inventory inventory = inventoryService.getInventory();
        if (inventory != null) {
            return ResponseEntity.ok(inventory);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
