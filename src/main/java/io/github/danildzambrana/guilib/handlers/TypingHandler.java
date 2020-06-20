package io.github.danildzambrana.guilib.handlers;

import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.data.TypingData;
import io.github.danildzambrana.guilib.data.objective.ActionObjective;
import io.github.danildzambrana.guilib.data.objective.SimpleObjective;
import io.github.danildzambrana.guilib.events.TypingActionEvent;
import io.github.danildzambrana.guilib.holder.SingleHolder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.Inventory;

import java.util.Map;
import java.util.UUID;

public class TypingHandler implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Map<UUID, TypingData> typingDataMap = TypingData.TYPING_DATA_MAP;
        Player player = event.getPlayer();

        if (!typingDataMap.containsKey(player.getUniqueId())) {
            return;
        }

        TypingData typingData = typingDataMap.get(player.getUniqueId());


        Inventory inventory = typingData.getInventory();

        if (!(inventory.getHolder() instanceof SingleHolder)) {
            return;
        }

        TypingActionEvent typingActionEvent = new TypingActionEvent(player, event.getMessage(), typingData);
        Bukkit.getPluginManager().callEvent(typingActionEvent);

        if (typingActionEvent.isCancelled()) {
            return;
        }

        event.setCancelled(true);
        typingDataMap.remove(player.getUniqueId());

        if (event.getMessage().equalsIgnoreCase("--cancel")) {
            player.openInventory(typingData.getInventory());
            return;
        }

        if (!event.getMessage().matches(typingData.getRegex())) {
            player.sendMessage(typingData.getErrorMessage());
            return;
        }

        SingleHolder holder = (SingleHolder) typingData.getInventory().getHolder();
        Button button = holder.getButtonMap().get(typingData.getSlotClicked());

        if (button == null) {
            return;
        }

        String text = ChatColor.translateAlternateColorCodes('&', event.getMessage());

        if (typingData.getObjective() instanceof ActionObjective) {
            ((ActionObjective) typingData.getObjective()).getAction().execute(text);
            return;
        }

        if (typingData.getObjective() instanceof SimpleObjective) {
            SimpleObjective.Objective objective = ((SimpleObjective) typingData.getObjective()).getObjective();

            if (objective == SimpleObjective.Objective.ITEM_LORE) {
                button.addLore(text);
            } else if (objective == SimpleObjective.Objective.ITEM_NAME) {
                button.setName(text);
            }
        }
        holder.setButton(typingData.getSlotClicked(), button);
        player.openInventory(holder.getInventory());
    }
}
