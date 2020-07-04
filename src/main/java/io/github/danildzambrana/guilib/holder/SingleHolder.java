package io.github.danildzambrana.guilib.holder;

import io.github.danildzambrana.guilib.button.Button;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public class SingleHolder extends BaseHolder {
    public SingleHolder(int size, String title, boolean blockAllSlots) {
        super(size, title, new HashMap<>(), blockAllSlots);
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, super.getSize(), super.getTitle());

        for (Map.Entry<Integer, Button> entry : super.getButtonMap().entrySet()) {
            inventory.setItem(entry.getKey(), entry.getValue().getItemStack());
        }

        return inventory;
    }
}
