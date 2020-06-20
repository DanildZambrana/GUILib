package io.github.danildzambrana.guilib.handlers;

import io.github.danildzambrana.guilib.holder.PaginatedHolder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PaginatedHandler extends MenuHandler implements Listener {
    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getView().getTopInventory().getHolder() instanceof PaginatedHolder)) {
            return;
        }

        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }

        //Prepared to future :)
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();

        if (itemStack == null || itemStack.getType() == Material.AIR) {
            return;
        }

        PaginatedHolder paginatedHolder = (PaginatedHolder) event.getView().getTopInventory().getHolder();
        super.execute(paginatedHolder, event, player);
    }


}
