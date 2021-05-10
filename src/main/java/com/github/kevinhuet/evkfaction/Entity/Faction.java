package com.github.kevinhuet.evkfaction.Entity;

import com.github.kevinhuet.evkfaction.Service.FactionManager;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Faction {

    private String name;
    private String description;
    private Location home;
    private double power;
    private double powerMax;
    private double powerMin;
    private Role role = Role.RECRUIT;
    private List<Claim> claims = new ArrayList<>();
    private List<FactionPlayer> players = new ArrayList<>();
    private FactionType factionType = FactionType.NORMAL;
    private Map<String, Relation> relations = new HashMap<>();

    public Faction(String name, FactionPlayer creator) {
        this.players.add(creator);
        this.name = name;
        this.power = 10 * this.players.size();
        FactionManager.getInstance().addFaction(this);
    }

    public Faction(String name, FactionPlayer creator, FactionType type) {
        this.players.add(creator);
        this.name = name;
        this.factionType = type;
        this.power = 10 * this.players.size();
        FactionManager.getInstance().addFaction(this);
    }

    public Claim claim(Chunk chunk) {
        Claim claim = new Claim(this, chunk);
        Faction target = FactionManager.getInstance().getFactionByChunk(chunk);
        if (target.getFactionType() == FactionType.FREEZONE) {
            if (this.claims.size() + 1 <= this.power);
        } else if (target.getFactionType() == FactionType.NORMAL) {
            if (target.isClaimable()) {
                target.claims.remove(target.getClaimFromChunk(chunk));
                this.claims.add(claim);
            }
        }

        return claim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPower() {
        return power;
    }

    public void setPower(float power) {
        this.power = power;
    }

    public List<FactionPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<FactionPlayer> players) {
        this.players = players;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isNegative() {
        return this.power < 0;
    }

    public boolean isPositive() {
        return this.power >= 0;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getPowerMax() {
        return powerMax;
    }

    public void setPowerMax(double powerMax) {
        this.powerMax = powerMax;
    }

    public double getPowerMin() {
        return powerMin;
    }

    public void setPowerMin(double powerMin) {
        this.powerMin = powerMin;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }

    public FactionType getFactionType() {
        return factionType;
    }

    public void setFactionType(FactionType factionType) {
        this.factionType = factionType;
    }

    public boolean isClaimable() {
        return this.claims.size() > this.power;
    }

    public Claim getClaimFromChunk(Chunk chunk) {
        return this.claims.stream().filter(c -> c.getChunk().equals(chunk)).findAny().orElse(null);
    }

    public Faction setEnemy(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target != null)
            this.relations.put(targetName, Relation.ENEMY);
        return target;
    }

    public Faction setAlly(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target != null)
            this.relations.put(targetName, Relation.ALLY);
        return target;
    }

    public Faction setNeutral(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target != null)
            this.relations.put(targetName, Relation.NEUTRAL);
        return target;
    }

    public boolean isSpecialFaction() {
        return (this.factionType != FactionType.FREEZONE && this.factionType != FactionType.NORMAL);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Relation> getRelations() {
        return relations;
    }

    public void setRelations(Map<String, Relation> relations) {
        this.relations = relations;
    }

    public void sendMessageForAllMembers(String[] message) {
        for (FactionPlayer fp : getPlayers()) {
            fp.getPlayer().sendMessage(message);
        }
    }
}
