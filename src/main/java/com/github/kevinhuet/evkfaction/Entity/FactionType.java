package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum FactionType implements Serializable {

    SAFEZONE(3, "Safezone"),
    WARZONE(2, "Warzone"),
    NORMAL(1, "Faction"),
    FREEZONE(0, "Freezone");

    private int value;
    private String name;

    FactionType(int i, String s) {
        this.value = i;
        this.name = s;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean pvpIsEnabled() {
        return this.value != SAFEZONE.value;
    }

    public boolean canInteract(Block block) {
        return true;
    }
}
