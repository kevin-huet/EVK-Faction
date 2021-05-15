package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Flags implements Serializable {

    private boolean pvp = true;
    private boolean explosion = true;
    private boolean monsters = true;
    private boolean powerloss = true;
    private boolean permanent = false;
    private boolean open = false;
    private boolean friendlyfire = false;
    private boolean firespread = true;
    private boolean endergrief = false;
    private boolean grief = false;
    private boolean liquidPropagation = true;
    private boolean pistonPush = true;
    private boolean creeperExplosion = true;
    Map<Material, Boolean> map = Stream.of(new Object[][] {
            { Material.CHEST, true },
            { Material.STONE_BUTTON, true },
            { Material.WOOD_BUTTON, true },
            { Material.FURNACE, true },
            { Material.ENCHANTMENT_TABLE, true },
            { Material.DROPPER, true },
            { Material.DISPENSER, true },
            { Material.ANVIL, true }
    }).collect(Collectors.toMap(data -> (Material) data[0], data -> (Boolean) data[1]));

    public boolean isPvp() {
        return pvp;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
    }

    public boolean isExplosion() {
        return explosion;
    }

    public void setExplosion(boolean explosion) {
        this.explosion = explosion;
    }

    public boolean isMonsters() {
        return monsters;
    }

    public void setMonsters(boolean monsters) {
        this.monsters = monsters;
    }

    public boolean isPowerloss() {
        return powerloss;
    }

    public void setPowerloss(boolean powerloss) {
        this.powerloss = powerloss;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isFriendlyfire() {
        return friendlyfire;
    }

    public void setFriendlyfire(boolean friendlyfire) {
        this.friendlyfire = friendlyfire;
    }

    public boolean isFirespread() {
        return firespread;
    }

    public void setFirespread(boolean firespread) {
        this.firespread = firespread;
    }

    public boolean isEndergrief() {
        return endergrief;
    }

    public void setEndergrief(boolean endergrief) {
        this.endergrief = endergrief;
    }

    public boolean isGrief() {
        return grief;
    }

    public void setGrief(boolean grief) {
        this.grief = grief;
    }

    public boolean isLiquidPropagation() {
        return liquidPropagation;
    }

    public void setLiquidPropagation(boolean liquidPropagation) {
        this.liquidPropagation = liquidPropagation;
    }

    public boolean isPistonPush() {
        return pistonPush;
    }

    public void setPistonPush(boolean pistonPush) {
        this.pistonPush = pistonPush;
    }

    public boolean isCreeperExplosion() {
        return creeperExplosion;
    }

    public void setCreeperExplosion(boolean creeperExplosion) {
        this.creeperExplosion = creeperExplosion;
    }
}
