package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.App;
import com.github.kevinhuet.evkfaction.Entity.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.*;

public class ConfigManager {

    private FileConfiguration config;
    private static ConfigManager instance = null;
    final static String BASE = ".Faction";

    public ConfigManager() {
        this.config = App.getInstance().getConfig();
    }

    public static ConfigManager getInstance() {
        if (instance == null)
            instance = new ConfigManager();
        return instance;
    }

    private void save() {
        App.getInstance().saveConfig();
    }


    public void initConfig() {

    }

    public void initFactions() {
        if (this.config == null)
            return;
        for (Object obj : this.config.getConfigurationSection(".Faction.factions").getKeys(false)) {
            App.getInstance().getServer().broadcastMessage(obj.toString());
            //FactionManager.getInstance().addFaction(getFactionFromUUID(UUID.fromString(obj.toString())));
        }
    }

    public void addFaction(Faction faction) {
        List<Integer[]> locationChunks = new ArrayList<>();
        int i  = 1;
        this.config.set(BASE+".factions."+faction.getId().toString()+".name", faction.getName());
        this.config.set(BASE+".factions."+faction.getId().toString()+".power", faction.getPower());
        this.config.set(BASE+".factions."+faction.getId().toString()+".powerMax", faction.getPowerMax());
        this.config.set(BASE+".factions."+faction.getId().toString()+".factionType", faction.getFactionType());
        this.config.set(BASE+".factions."+faction.getId().toString()+".home", faction.getHome());
        this.config.set(BASE+".factions."+faction.getId().toString()+".flags", faction.getFlags());
        this.config.set(BASE+".factions."+faction.getId().toString()+".description", faction.getDescription());

        for (Map.Entry<UUID, Relation> entry : faction.getRelations().entrySet()) {
            this.config.set(BASE+".factions."+faction.getId().toString()+".relations."+entry.getKey(), entry.getValue());
        }

        for (Claim claim : faction.getClaims()) {
            Integer[] array = {claim.getChunk().getX(), claim.getChunk().getZ()};
            this.config.set(BASE+".factions."+faction.getId().toString()+".claims."+i+"world", claim.getChunk().getWorld());
            this.config.set(BASE+".factions."+faction.getId().toString()+".claims."+i+"location", array);
            i++;
        }

        save();
    }

    public Faction getFactionFromUUID(UUID uuid) {
        Faction faction = new Faction();
        Claim claim = null;
        Map<UUID, Relation> relations = new HashMap<>();
        faction.setName(this.config.getString(BASE+".factions."+uuid.toString()+".name"));
        faction.setPower(this.config.getDouble(BASE+".factions."+uuid.toString()+".power"));
        faction.setPowerMax(this.config.getDouble(BASE+".factions."+uuid.toString()+".powerMax"));
        faction.setFactionType((FactionType) this.config.get(BASE+".factions."+uuid.toString()+".factionType"));
        faction.setHome((Location) this.config.get(BASE+".factions."+uuid.toString()+".home"));
        faction.setFlags((Flags) this.config.get(BASE+".factions."+uuid.toString()+".flags"));
        faction.setDescription(this.config.getString(BASE+".factions."+uuid.toString()+".description"));

        for (Object obj : this.config.getConfigurationSection(BASE+".factions."+uuid.toString()+".claim").getKeys(false)) {
            Integer[] loc = this.config.getIntegerList(BASE + ".factions." + uuid.toString() + ".claim." + obj.toString() + ".location").toArray(new Integer[0]);
            String str = this.config.getString(BASE + ".factions." + uuid.toString() + ".claim."+obj.toString()+".world");
            claim = new Claim(faction, Bukkit.getWorld(str).getChunkAt(loc[0], loc[1]));
            faction.addClaims(claim);
        }

        for (Object obj :  this.config.getConfigurationSection(BASE+".factions."+uuid.toString()+".relations").getKeys(false)) {
            relations.put(UUID.fromString(obj.toString()), (Relation) this.config.get(BASE+".factions."+uuid.toString()+".relations."+obj.toString()));
        }
        faction.setRelations(relations);
        return faction;
    }

    public void removeFaction(Faction faction) {

    }

    public void addPlayer(FactionPlayer player) {
        this.config.set(BASE+".players."+player.getPlayerUUID(), player);
        save();
    }

    public FactionPlayer getPlayerFromUUID(UUID uuid) {
        return (FactionPlayer) this.config.get(BASE+".players."+uuid);
    }

    public void removePlayer(FactionPlayer player) {

    }
}
