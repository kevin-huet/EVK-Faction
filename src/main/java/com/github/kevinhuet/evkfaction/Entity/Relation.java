package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.ChatColor;

public enum Relation {

    MEMBER(3, "Member"),
    ALLY(2, "Ally"),
    NEUTRAL(1, "Neutral"),
    ENEMY(0, "Enemy"),
    ;

    private int value;
    private String name;
    private ChatColor color;

    Relation(int i, String s) {
        this.value = i;
        this.name = s;
        this.color = (new ChatColor[]{
                ChatColor.RED,
                ChatColor.WHITE,
                ChatColor.LIGHT_PURPLE,
                ChatColor.GREEN
        })[i];
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

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.color = color;
    }
}
