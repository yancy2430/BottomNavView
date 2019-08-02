package com.tdeado.bottomnav;

/**
 * Created by yangzhe on 2017/2/25.
 */

public class MenuItem {
    private String name;
    private int icon;

    public MenuItem(String name, int icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
