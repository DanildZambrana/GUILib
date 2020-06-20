package io.github.danildzambrana.guilib.data;

import io.github.danildzambrana.guilib.data.objective.AbstractObjective;
import org.bukkit.inventory.Inventory;

public class TypingData extends TransferData {
    private final int slotClicked;
    private final Inventory inventory;
    private final String regex;
    private final String errorMessage;
    private final AbstractObjective objective;

    public TypingData(int slotClicked,
                      Inventory inventory,
                      String regex,
                      String errorMessage,
                      AbstractObjective objective) {
        this.slotClicked = slotClicked;
        this.inventory = inventory;
        this.regex = regex;
        this.errorMessage = errorMessage;
        this.objective = objective;
    }

    public int getSlotClicked() {
        return slotClicked;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public String getRegex() {
        return regex;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public AbstractObjective getObjective() {
        return objective;
    }
}
