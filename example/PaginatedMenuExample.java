package example;

import io.github.danildzambrana.guilib.GUILib;
import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.menu.Menu;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.List;
import java.util.Map;

public class PaginatedMenuExample implements Menu {
    private final GUILib gui;

    public PaginatedMenuExample(Plugin plugin, PluginManager pluginManager) {
        this.gui = GUILib.getInstance(plugin, pluginManager);
    }

    @Override
    public Inventory getInventory(String title, Map<String, Object> data) {
        return gui.createPaginatedMenu((List<Button>) data.get("items")).getInventory();
    }

    @Override
    public void initializeItems() {
        //None Here
    }
}
