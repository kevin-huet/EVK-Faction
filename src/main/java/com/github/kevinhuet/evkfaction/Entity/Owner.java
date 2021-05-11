package com.github.kevinhuet.evkfaction.Entity;

public enum Owner {

    ALL(-1, "ALL"),
    BREAK(1, "BREAK"),
    PLACE(2, "PLACE"),
    INTERACT(3, "INTERACT"),
    CHEST(4, "CHEST");

    private int value;
    private String name;

    Owner(int i, String name) {
        this.value = i;
        this.name = name;
    }
}
