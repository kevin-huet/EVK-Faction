package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;

public enum FactionType {

    SAFEZONE(3, "Admin"),
    WARZONE(2, "Officer"),
    NORMAL(1, "Member"),
    FREEZONE(0, "recruit");

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
