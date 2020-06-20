package example;

import io.github.danildzambrana.guilib.GUILib;
import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.data.TransferData;
import io.github.danildzambrana.guilib.data.TypingData;
import io.github.danildzambrana.guilib.data.objective.SimpleObjective;
import io.github.danildzambrana.guilib.holder.SingleHolder;
import io.github.danildzambrana.guilib.menu.Menu;
import io.github.danildzambrana.guilib.menu.MenuManager;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SinglePageMenuExample implements Menu {
    private final GUILib gui;
    private Button sayHelloButton, sayBayButton, openOtherInventoryButton, inputTextButton;
    private final MenuManager menuManager;

    public SinglePageMenuExample(Plugin plugin, PluginManager pluginManager, MenuManager menuManager) {
        this.gui = GUILib.getInstance(plugin, pluginManager);
        this.menuManager = menuManager;
    }

    @Override
    public Inventory getInventory(String title, Map<String, Object> data) {
        SingleHolder holder = gui.createNewHolder("Sinlge page", 54, true);

        holder.setButton(1, sayHelloButton);
        holder.setButton(4, sayBayButton);
        holder.setButton(7, openOtherInventoryButton);
        holder.setButton(10, inputTextButton);

        return holder.getInventory();
    }

    @Override
    public void initializeItems() {
        sayHelloButton =
                gui.createButton(gui.getNewBuilder(Material.PAPER).build(), "Say Hello")
                        .addLeftClickAction(event -> event.getWhoClicked().sendMessage("Hello from GUILib!"));

        sayBayButton =
                gui.createButton(new ItemStack(Material.ACACIA_DOOR), "Say Bay")
                        .addLeftClickAction(event -> event.getWhoClicked().sendMessage("Bay from GUILib"));

        Map<String, Object> dataMap = new HashMap<>();

        List<Button> items = new ArrayList<>();
        items.add(gui.createButton(new ItemStack(Material.SLIME_BLOCK), ""));
        items.add(gui.createButton(new ItemStack(Material.STONE), ""));
        items.add(gui.createButton(new ItemStack(Material.STONE_BUTTON), "Click me"));

        dataMap.put("buttons", items);
        openOtherInventoryButton =
                gui.createButton(new ItemStack(Material.CHEST), "Open Other Inventory")
                        .addLeftClickAction(event -> event.getWhoClicked()
                                .openInventory(menuManager.getMenu(PaginatedMenuExample.class)
                                        .getInventory("", dataMap)));

        inputTextButton = gui.createButton(new ItemStack(Material.ANVIL), "").addLeftClickAction(event -> {
            TypingData
                    typingData =
                    new TypingData(event.getRawSlot(),
                            event.getInventory(),
                            "",
                            "An error has occurred",
                            new SimpleObjective(SimpleObjective.Objective.ITEM_NAME));
            TransferData.TYPING_DATA_MAP.put(event.getWhoClicked().getUniqueId(), typingData);
        });
    }
}
