package io.github.danildzambrana.guilib.holder;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;

public class SingleHolder extends BaseHolder implements InventoryHolder {
    public SingleHolder(int size, String title, boolean blockAllSlots) {
        super(size, title, new HashMap<>(), blockAllSlots);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, super.getSize(), super.getTitle());

        super.getButtonMap().forEach(inventory::setItem);

        return inventory;
    }
}
