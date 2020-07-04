package io.github.danildzambrana.guilib.button;

import io.github.danildzambrana.guilib.exceptions.ButtonNotAllowedHereException;
import io.github.danildzambrana.guilib.holder.PaginatedHolder;
import org.bukkit.inventory.ItemStack;

public class PreviousButton extends Button {

    public PreviousButton(ItemStack itemStack, String name) {
        super(itemStack, name);
        initialize();
    }

    public PreviousButton(Button baseButton) {
        super(baseButton);
        initialize();
    }

    private void initialize() {
        addLeftClickAction(event -> {
            if (!(event.getView().getTopInventory().getHolder() instanceof PaginatedHolder)) {
                try {
                    throw new ButtonNotAllowedHereException("This button is not allowed in not paginated menu!");
                } catch (ButtonNotAllowedHereException e) {
                    e.printStackTrace();
                }
            }

            PaginatedHolder holder = (PaginatedHolder) event.getView().getTopInventory().getHolder();

            event.getWhoClicked().openInventory(holder.getPreviousPage().getInventory());
        });
    }
}
