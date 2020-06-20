package io.github.danildzambrana.guilib.holder;

import io.github.danildzambrana.guilib.button.Button;
import org.bukkit.inventory.InventoryHolder;

import java.util.Map;

public abstract class BaseHolder implements InventoryHolder {
    private final int size;
    private final String title;
    private final Map<Integer, Button> buttonMap;
    private final boolean blockAllSlots;

    public BaseHolder(int size, String title, Map<Integer, Button> buttonMap, boolean blockAllSlots) {
        this.size = size;
        this.title = title;
        this.buttonMap = buttonMap;
        this.blockAllSlots = blockAllSlots;
    }

    public int getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, Button> getButtonMap() {
        return buttonMap;
    }

    public boolean isBlockAllSlots() {
        return blockAllSlots;
    }

    public void setButton(int slot, Button button) {
        this.buttonMap.put(slot, button);
    }
}
