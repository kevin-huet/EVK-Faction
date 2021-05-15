package com.github.kevinhuet.evkfaction.Entity;

import java.io.Serializable;

public enum Role implements Serializable {

    ADMIN(3, "Admin"),
    OFFICER(2, "Officer"),
    MEMBER(1, "Member"),
    RECRUIT(0, "recruit");

    private int value;
    private String name;

    Role(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public boolean isAtMost(Role role) {
        return this.value <= role.value;
    }

    public boolean isAtLeast(Role role) {
        return this.value >= role.value;
    }

    public boolean is(Role role) {
        return role == this;
    }

    public boolean isLessThan(Role role) {
        return this.value < role.value;
    }

    public boolean isMoreThan(Role role) {
        return this.value > role.value;
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
