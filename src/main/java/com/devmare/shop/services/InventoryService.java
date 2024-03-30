package com.devmare.shop.services;

import com.devmare.shop.models.Inventory;

public interface InventoryService {

    /**
     * This method is used to create an inventory.
     * 
     * @param inventory
     * @return Inventory
     */
    Inventory createInventory(Inventory inventory);

    /**
     * This method is used to get the inventory.
     * 
     * @return Inventory
     */
    Inventory getInventory();
}
