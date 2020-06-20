package io.github.danildzambrana.guilib.menu;

import org.bukkit.inventory.Inventory;

import java.util.Map;

public interface Menu {
    Inventory getInventory(String title, Map<String, Object> data);

    void initializeItems();
}
