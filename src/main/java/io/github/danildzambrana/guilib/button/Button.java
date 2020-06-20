package io.github.danildzambrana.guilib.button;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;


public class Button extends ItemStack implements Cloneable {
    private final List<ClickAction> leftClickActions = new ArrayList<>();
    private final List<ClickAction> rightClickActions = new ArrayList<>();
    private final List<ClickAction> middleClickActions = new ArrayList<>();
    private ItemStack itemStack;

    public Button(ItemStack itemStack, String name) {
        if (!name.isEmpty()) {
            ItemMeta meta = itemStack.getItemMeta();
            meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
            itemStack.setItemMeta(meta);
        }

        this.itemStack = itemStack;
    }

    public Button(Button baseButton) {
        this.itemStack = baseButton.getItemStack();
    }


    /**
     * Gets the {@link ItemStack} that represents this icon.
     *
     * @return The {@link ItemStack} that represents it.
     */
    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Set the new itemStack
     *
     * @param itemStack the new itemStack
     */
    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    /**
     * The {@link ClickAction} that will be executed when clicking with left click.
     *
     * @return The all {@link ClickAction}
     */
    public List<ClickAction> getLeftClickActions() {
        return leftClickActions;
    }

    /**
     * The {@link ClickAction} that will be executed when clicking with right click.
     *
     * @return The all {@link ClickAction}
     */
    public List<ClickAction> getRightClickActions() {
        return rightClickActions;
    }

    /**
     * The {@link ClickAction} that will be executed when clicking with middle click.
     *
     * @return The all {@link ClickAction}
     */
    public List<ClickAction> getMiddleClickActions() {
        return middleClickActions;
    }

    /**
     * Add a action to the left action list.
     *
     * @param action the action to add.
     */
    public Button addLeftClickAction(ClickAction action) {
        getLeftClickActions().add(action);
        return this;
    }

    /**
     * Add a action to the right action list.
     *
     * @param action the action to add.
     */
    public Button addRightClickAction(ClickAction action) {
        getRightClickActions().add(action);
        return this;
    }

    /**
     * Add a action to the middle action list.
     *
     * @param action the action to add.
     */
    public Button addMiddleClickAction(ClickAction action) {
        getMiddleClickActions().add(action);
        return this;
    }

    /**
     * Add a lore line.
     *
     * @param lore the line to add.
     * @return this icon.
     */
    public Button addLore(String lore) {
        ItemMeta meta = itemStack.getItemMeta();
        List<String> loreList = meta.getLore();
        if (loreList == null) {
            loreList = new ArrayList<>();
        }

        loreList.add(ChatColor.translateAlternateColorCodes('&', lore));
        meta.setLore(loreList);
        itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Clone this object
     *
     * @return a new cloned object
     */
    public Button clone() {
        return (Button) super.clone();
    }

    /**
     * Clear the lore.
     *
     * @return this icon.
     */
    public Button clearLore() {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setLore(null);
        itemStack.setItemMeta(meta);
        return this;
    }

    /**
     * Set a new name to this button.
     *
     * @param name the new name.
     */
    public Button setName(String name) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        itemStack.setItemMeta(meta);

        return this;
    }
}


