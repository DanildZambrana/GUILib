package io.github.danildzambrana.guilib.holder;

import io.github.danildzambrana.guilib.button.Button;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

import java.util.Map;
import java.util.function.Consumer;

public abstract class BaseHolder implements InventoryHolder {
    private final int size;
    private final String title;
    private final Map<Integer, Button> buttonMap;
    private final boolean blockAllSlots;
    private Consumer<InventoryCloseEvent> onClose;
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

    public void setOnClose(Consumer<InventoryCloseEvent> onClose) {
        this.onClose = onClose;
    }

    public void executeClose(InventoryCloseEvent event) {
        if (this.onClose != null) {
            this.onClose.accept(event);
        }
    }
}
