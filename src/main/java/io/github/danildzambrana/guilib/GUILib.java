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
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public class GUILib {
    private static volatile GUILib instance = null;

    private GUILib(Plugin plugin, @NotNull PluginManager manager) {
        manager.registerEvents(new PaginatedHandler(), plugin);
        manager.registerEvents(new SinglePageHandler(), plugin);
        manager.registerEvents(new TypingHandler(), plugin);
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

    public SingleHolder createNewHolder(String name, int size, boolean blockAllSlots) {
        return new SingleHolder(size, name, blockAllSlots);
    }

    public Button createButton(ItemStack itemStack, String name) {
        return new Button(itemStack, name);
    }

    public PaginatedHolder createPaginatedMenu(@NotNull List<Button> buttons) {
        int inventorySize = 46;
        int pagesSize = buttons.size() / inventorySize;

        Iterator<Button> buttonIterable = buttons.iterator();
        PaginatedHolder previous = new PaginatedHolder("1/" + pagesSize, true, null, null);
        for (int i = 1; i < pagesSize; i++) {
            String title = (i + 1) + "/" + pagesSize;
            PaginatedHolder holder = new PaginatedHolder(title, true, previous, null);

            for (int x = 0; x < inventorySize; x++) {
                holder.setButton(x, buttonIterable.next());
            }

            previous.setNextPage(holder);
            previous = holder;
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

}