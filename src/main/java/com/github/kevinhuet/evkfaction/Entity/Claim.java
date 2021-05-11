package com.github.kevinhuet.evkfaction.Entity;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.io.Serializable;

public class Claim implements Serializable {

    private Faction faction;
    private Chunk chunk;
    private Owner owner;

    public Claim(Faction faction, Chunk chunk) {
        this.faction = faction;
        this.chunk = chunk;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public void setChunk(Chunk chunk) {
        this.chunk = chunk;
    }
}
