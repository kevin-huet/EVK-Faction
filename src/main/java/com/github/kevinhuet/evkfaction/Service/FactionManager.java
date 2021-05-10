package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.Entity.Claim;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FactionManager {

    private static FactionManager instance = null;
    private Map<String, Faction> factions = new HashMap<>();


    public FactionManager() {

    }

    public static FactionManager getInstance() {
        if (instance == null) {
            instance = new FactionManager();
        }
        return instance;
    }

    public static void setInstance(FactionManager instance) {
        FactionManager.instance = instance;
    }

    public Faction getFactionByName(String name) {
        return this.factions.get(name);
    }

    public void addFaction(Faction faction) {
        this.factions.put(faction.getName(), faction);
    }

    public Map<String, Faction> getFactions() {
        return this.factions;
    }

    public void setFactions(Map<String, Faction> factions) {
        this.factions = factions;
    }

    public Faction getFactionByChunk(Chunk chunk) {
        for (Faction faction: this.factions.values()) {
            for (Claim claim : faction.getClaims()) {
                if (claim.getChunk() == chunk)
                    return faction;
            }
        }
        return null;
    }
}
