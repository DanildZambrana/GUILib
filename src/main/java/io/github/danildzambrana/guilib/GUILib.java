/*
 * Copyright (c) 2020. This file is subject to the terms and conditions defined in file 'LICENSE.md', which is part of this source code package.
 */

package io.github.danildzambrana.guilib;

import io.github.danildzambrana.guilib.button.Button;
import io.github.danildzambrana.guilib.handlers.PaginatedHandler;
import io.github.danildzambrana.guilib.handlers.SinglePageHandler;
import io.github.danildzambrana.guilib.handlers.TypingHandler;
import io.github.danildzambrana.guilib.holder.PaginatedHolder;
import io.github.danildzambrana.guilib.holder.SingleHolder;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class GUILib {
    private static volatile GUILib instance = null;
    private static Plugin plugin;

    private GUILib(Plugin plugin, @NotNull PluginManager manager) {
        GUILib.plugin = plugin;
        manager.registerEvents(new PaginatedHandler(), plugin);
        manager.registerEvents(new SinglePageHandler(), plugin);
        manager.registerEvents(new TypingHandler(), plugin);
        ConfigurationSerialization.registerClass(Button.class);
    }

    public static GUILib getInstance(Plugin plugin, @NotNull PluginManager manager) {
        if (instance == null) {

            //Thread secure
            synchronized (GUILib.class) {
                if (instance == null) {
                    instance = new GUILib(plugin, manager);
                }
            }
        }

        return instance;
    }

    public ItemBuilder getNewBuilder(Material material) {
        return new ItemBuilder(material);
    }

    public LeatherBuilder getNewLeatherBuilder(Material material) {
        return new LeatherBuilder(material);
    }

    public SingleHolder createNewHolder(String name, int size, boolean blockAllSlots) {
        return new SingleHolder(size, name, blockAllSlots);
    }

    public Button createButton(ItemStack itemStack, String name) {
        return new Button(itemStack, name);
    }

    public PaginatedHolder createPaginatedMenu(@NotNull List<Button> buttons) {
        double inventorySize = 46;
        int pagesSize = 0;

        double pages = buttons.size() / inventorySize;

        if (pages - Math.floor(pages) < 1 && pages - Math.floor(pages) > 0) {
            pagesSize++;
        }
        pagesSize += Math.floor(pages);

        Iterator<Button> buttonIterable = buttons.iterator();
        PaginatedHolder previous = new PaginatedHolder("1/" + pagesSize, true, null, null);
        for (int i = 0; i < pagesSize; i++) {
            String title = (i + 1) + "/" + pagesSize;
            PaginatedHolder holder = new PaginatedHolder(title, true, previous, null);

            for (int x = 0; (x < inventorySize) && buttonIterable.hasNext(); x++) {
                holder.setButton(x, buttonIterable.next());
            }

            previous.setNextPage(holder);
            previous = holder;
        }

        while (previous.getPreviousPage() != null) {
            previous = previous.getPreviousPage();
        }
        return previous;
    }

    public static class ItemBuilder {
        private final ItemStack item;

        private ItemBuilder(Material material) {
            this.item = new ItemStack(material, 1);
        }

        public ItemStack build() {
            return this.item;
        }

        public ItemBuilder setByte(short by) {
            item.setDurability(by);
            return this;
        }
    }

    public static class LeatherBuilder {
        private final ItemStack item;

        public LeatherBuilder(Material material) {
            this.item = new ItemStack(material);
        }

        public LeatherBuilder setColor(int r, int g, int b){
            return setColor(Color.fromRGB(r, g, b));
        }

        public LeatherBuilder setColor(Color color){
            ItemMeta meta = item.getItemMeta();

            if(!(meta instanceof LeatherArmorMeta)) {
                plugin.getLogger().severe("The item `" + item.getType().name() + "' not is an Leather Armor");
                return this;
            }

            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) meta;

            leatherArmorMeta.setColor(color);

            item.setItemMeta(leatherArmorMeta);
            return this;
        }

        public ItemStack build() {
            return this.item;
        }
    }

}