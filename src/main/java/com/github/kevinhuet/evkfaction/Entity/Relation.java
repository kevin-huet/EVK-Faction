package com.github.kevinhuet.evkfaction.Entity;

public enum Relation {

    MEMBER(3, "Member"),
    ALLY(2, "Ally"),
    NEUTRAL(1, "Neutral"),
    ENEMY(0, "Enemy"),
    ;

    private int value;
    private String name;

    Relation(int i, String s) {
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
}
