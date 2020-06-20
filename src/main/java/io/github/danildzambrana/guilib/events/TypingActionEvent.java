package io.github.danildzambrana.guilib.events;

import io.github.danildzambrana.guilib.data.TypingData;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;


public class TypingActionEvent extends Event implements Cancellable {
    private final HandlerList handlerList = new HandlerList();
    private final Player player;
    private final String text;
    private final TypingData data;
    private boolean cancelled;

    public TypingActionEvent(Player player, String text, TypingData data) {
        this.player = player;
        this.text = text;
        this.data = data;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
