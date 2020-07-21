package io.github.danildzambrana.guilib.holder;

import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.button.NextButton;
import io.github.danildzambrana.guilib.button.PreviousButton;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class PaginatedHolder extends BaseHolder implements InventoryHolder {

    private PaginatedHolder previousPage;
    private PaginatedHolder nextPage;

    public PaginatedHolder(String title,
                           boolean blockAllSlots,
                           PaginatedHolder previousPage,
                           PaginatedHolder nextPage) {
        super(54, title, new HashMap<>(), blockAllSlots);
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        loadButtonBar();
    }

    @Override
    public Inventory getInventory() {
        Inventory inventory = Bukkit.createInventory(this, 54, super.getTitle());

        for (Map.Entry<Integer, Button> entry : super.getButtonMap().entrySet()) {
            inventory.setItem(entry.getKey(),  entry.getValue().getItemStack());
        }
        return inventory;
    }

    private void loadButtonBar() {
        if (previousPage != null) {
            super.getButtonMap().put(45, new PreviousButton(new ItemStack(Material.REDSTONE_BLOCK), "Previous"));
        }

        if (nextPage != null) {
            super.getButtonMap().put(53, new NextButton(new ItemStack(Material.SLIME_BLOCK), "Next"));
        }
    }

    public PaginatedHolder setPreviousButton(Button previousButton) {
        if (previousPage != null) {
            super.getButtonMap().put(45, previousButton);
        }
        return this;
    }

    public PaginatedHolder setNextButton(Button nextButton) {
        if (nextPage != null) {
            super.getButtonMap().put(53, nextButton);
        }
        return this;
    }

    public PaginatedHolder getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(PaginatedHolder previousPage) {
        this.previousPage = previousPage;
    }

    public PaginatedHolder getNextPage() {
        return nextPage;
    }

    public void setNextPage(PaginatedHolder nextPage) {
        this.nextPage = nextPage;
    }
}
