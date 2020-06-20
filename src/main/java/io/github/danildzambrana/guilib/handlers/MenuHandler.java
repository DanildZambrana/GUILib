package io.github.danildzambrana.guilib.handlers;

import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.button.ClickAction;
import io.github.danildzambrana.guilib.events.ClickButtonEvent;
import io.github.danildzambrana.guilib.exceptions.ClickActionException;
import io.github.danildzambrana.guilib.holder.BaseHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.jetbrains.annotations.NotNull;

public class MenuHandler {
    protected void execute(@NotNull BaseHolder holder, @NotNull InventoryClickEvent event, Player player) {
        Button button = holder.getButtonMap().get(event.getRawSlot());

        if (button == null) {
            if (holder.isBlockAllSlots()) {
                event.setCancelled(true);
            }
            return;
        }

        event.setCancelled(true);

        ClickButtonEvent clickButtonEvent = new ClickButtonEvent(player, button, holder);
        Bukkit.getPluginManager().callEvent(clickButtonEvent);

        if (clickButtonEvent.isCancelled()) {
            return;
        }

        for (ClickAction leftClickAction : button.getLeftClickActions()) {
            try {
                leftClickAction.execute(event);
            } catch (ClickActionException e) {
                e.printStackTrace();
            }
        }

        for (ClickAction rightClickAction : button.getRightClickActions()) {
            try {
                rightClickAction.execute(event);
            } catch (ClickActionException e) {
                e.printStackTrace();
            }
        }

        for (ClickAction middleClickAction : button.getMiddleClickActions()) {
            try {
                middleClickAction.execute(event);
            } catch (ClickActionException e) {
                e.printStackTrace();
            }
        }
    }
}
