package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Flags {

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
}
