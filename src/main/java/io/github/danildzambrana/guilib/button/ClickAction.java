package io.github.danildzambrana.guilib.button;

import io.github.danildzambrana.guilib.exceptions.ClickActionException;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface ClickAction {
    /**
     * Execute the action provided.
     *
     * @param event the event to use in the action.
     */
    void execute(InventoryClickEvent event) throws ClickActionException;
}
