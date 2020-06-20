package io.github.danildzambrana.guilib.events;

import io.github.danildzambrana.guilib.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryHolder;

public class ClickButtonEvent extends Event implements Cancellable {
    private final HandlerList handlerList = new HandlerList();
    private Player player;
    private Button clicked;
    private InventoryHolder holder;
    private boolean cancelled;

    public ClickButtonEvent(Player player, Button clicked, InventoryHolder holder) {
        this.player = player;
        this.clicked = clicked;
        this.holder = holder;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }


    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Button getClicked() {
        return clicked;
    }

    public void setClicked(Button clicked) {
        this.clicked = clicked;
    }

    public InventoryHolder getHolder() {
        return holder;
    }

    public void setHolder(InventoryHolder getHolder) {
        this.holder = getHolder;
    }
}
