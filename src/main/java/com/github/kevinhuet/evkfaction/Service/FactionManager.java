package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.Entity.Claim;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import org.bukkit.Chunk;

import java.util.ArrayList;
import java.util.List;

public class FactionManager {

    private static FactionManager instance = null;
    private List<Faction> factions = new ArrayList<>();


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
        for (Faction faction : this.factions) {
            if (faction.getName() == name)
                return faction;
        }
        return null;
    }

    public void addFaction(Faction faction) {
        this.factions.add(faction);
    }

    public List<Faction> getFactions() {
        return this.factions;
    }

    public void setFactions(List<Faction> factions) {
        this.factions = factions;
    }

    public Faction getFactionByChunk(Chunk chunk) {
        for (Faction faction : this.factions) {
            for (Claim claim : faction.getClaims()) {
                if (claim.getChunk() == chunk)
                    return faction;
            }
        }
        return null;
    }
}
