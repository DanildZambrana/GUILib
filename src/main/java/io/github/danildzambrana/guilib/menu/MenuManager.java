package io.github.danildzambrana.guilib.menu;

import java.util.HashMap;
import java.util.Map;

public class MenuManager {
    private final Map<Class<? extends Menu>, Menu> menuMap = new HashMap<>();

    public void registerMenu(Menu menu) {
        menuMap.put(menu.getClass(), menu);
    }

    public Menu getMenu(Class<? extends Menu> clazz) {
        return menuMap.get(clazz);
    }

}
