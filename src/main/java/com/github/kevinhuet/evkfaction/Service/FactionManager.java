package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.Entity.Claim;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import org.bukkit.Chunk;

import java.util.*;

public class FactionManager {

    private static FactionManager instance = null;
    private Map<UUID, Faction> factions = new HashMap<>();


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
        for (Map.Entry<UUID, Faction> entry : this.factions.entrySet()) {
            if (entry.getValue().getName().equals(name))
                return entry.getValue();
        }
        return null;
    }

    public void addFaction(Faction faction) {
        this.factions.put(faction.getId(), faction);
    }

    public void renameFaction(Faction faction, String newName) {
        faction.setName(newName);
    }

    public Map<UUID, Faction> getFactions() {
        return this.factions;
    }

    public void setFactions(Map<UUID, Faction> factions) {
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
