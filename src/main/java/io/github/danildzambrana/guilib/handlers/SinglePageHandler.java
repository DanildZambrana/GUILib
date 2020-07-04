package io.github.danildzambrana.guilib.handlers;

import io.github.danildzambrana.guilib.holder.SingleHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SinglePageHandler extends MenuHandler implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onInventoryClick(@NotNull InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof SingleHolder)) {
            return;
        }

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        SingleHolder singleHolder = (SingleHolder) event.getView().getTopInventory().getHolder();
        super.execute(singleHolder, event, player);
    }

    @EventHandler(ignoreCancelled = true)
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof SingleHolder)){
            return;
        }

        if(!(event.getPlayer() instanceof Player)){
            return;
        }

        SingleHolder singleHolder = (SingleHolder) event.getView().getTopInventory().getHolder();
        singleHolder.executeClose(event);
    }
}
