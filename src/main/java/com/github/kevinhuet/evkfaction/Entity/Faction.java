package com.github.kevinhuet.evkfaction.Entity;

import com.github.kevinhuet.evkfaction.Service.FactionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.*;

public class Faction implements Serializable {


    private UUID id;
    private String name;
    private String description;
    private Location home;
    private double power;
    private double powerMax;
    private double powerMin;
    private Flags flags = new Flags();
    private List<Claim> claims = new ArrayList<>();
    private List<FactionPlayer> players = new ArrayList<>();
    private FactionType factionType = FactionType.NORMAL;
    private Map<UUID, Relation> relations = new HashMap<>();
    private Map<FactionPlayer, Long> invitations = new HashMap<>();

    public Faction(String name, FactionPlayer creator) {
        this.id = UUID.randomUUID();
        this.players.add(creator);
        this.name = name;
        this.powerMax = 10 * this.players.size();
        FactionManager.getInstance().addFaction(this);
    }

    public Faction() {
        FactionManager.getInstance().addFaction(this);
    }
    public Faction(String name, FactionPlayer creator, FactionType type) {
        this.id = UUID.randomUUID();
        this.players.add(creator);
        this.name = name;
        this.factionType = type;
        this.powerMax = 10 * this.players.size();
        FactionManager.getInstance().addFaction(this);
    }

    public Claim claim(FactionPlayer factionPlayer, Chunk chunk) {
        Claim claim = new Claim(this, chunk);
        Faction target = FactionManager.getInstance().getFactionByChunk(chunk);
        Claim existClaim = getClaimFromChunk(chunk);
        if (existClaim != null) {
            factionPlayer.getPlayer().sendMessage("test");
        }
        if (target == null || target.getFactionType() == FactionType.FREEZONE) {
            if (existClaim == null || existClaim.getFaction() != this) {
                this.claims.add(claim);
                for (Claim c : this.getClaims()) {
                    factionPlayer.getPlayer().sendMessage(c.getChunk().toString());

                }
                sendMessageForAllMembers(ChatColor.YELLOW + factionPlayer.getPlayer().getName() +
                        ChatColor.GREEN + " claimed a land at the coordinates : " +
                        factionPlayer.getPlayer().getLocation().getChunk().getX() + ", " + factionPlayer.getPlayer().getLocation().getChunk().getZ()
                );
            } else if (existClaim.getFaction() == this) {
                factionPlayer.getPlayer().sendMessage("you already have this land");
            }
        }
        return claim;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
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

    public void addPlayer(FactionPlayer player) {
        this.players.add(player);
        this.powerMax = 10 * this.players.size();
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
        return this.claims.stream().filter(c -> c.getChunk() == chunk).findFirst().orElse(null);
    }

    public Faction setEnemy(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target == null)
            return target;
        this.sendMessageForAllMembers(ChatColor.RED+target.getName()+" is now enemy");
        target.sendMessageForAllMembers(ChatColor.RED+this.getName()+" is now enemy");
        this.relations.put(target.getId(), Relation.ENEMY);
        return target;
    }

    public Faction setAlly(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target != null)
            this.relations.put(target.getId(), Relation.ALLY);
        return target;
    }

    public Faction setNeutral(String targetName) {
        Faction target = FactionManager.getInstance().getFactionByName(targetName);

        if (target != null)
            this.relations.put(target.getId(), Relation.NEUTRAL);
        return target;
    }

    public boolean isSpecialFaction() {
        return (this.factionType != FactionType.FREEZONE && this.factionType != FactionType.NORMAL);
    }

    public String getDescription() {
        return (description == null) ? "" : description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<UUID, Relation> getRelations() {
        return relations;
    }


    public void sendMessageForAllMembers(String message) {
        for (FactionPlayer fp : getPlayers()) {
            fp.getPlayer().sendMessage(message);
        }
    }

    public void invitePlayer(FactionPlayer player) {
        this.invitations.put(player, Calendar.getInstance().getTimeInMillis());
        player.getPlayer().sendMessage(new String[]{
                ChatColor.GREEN+"You have been invited by the faction "+ChatColor.YELLOW+this.getName(),
                ChatColor.GREEN+"use '/f join "+this.getName()+"' to join the faction",
        });
    }

    public void rename(String name) {
        FactionManager.getInstance().renameFaction(this, name);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Map<FactionPlayer, Long> getInvitations() {
        return invitations;
    }

    public void setInvitations(Map<FactionPlayer, Long> invitations) {
        this.invitations = invitations;
    }

    public void setRelations(Map<UUID, Relation> relations) {
        this.relations = relations;
    }

    public Relation checkRelation(Faction target) {
        if (target.getRelations().get(this.getId()) == Relation.ENEMY || relations.get(target.getId()) == Relation.ENEMY)
            return Relation.ENEMY;
        else if (target.getRelations().get(this.getId()) == Relation.ALLY || relations.get(target.getId()) == Relation.ALLY)
            return Relation.ALLY;
        return Relation.NEUTRAL;
    }

    public void addClaims(Claim claim) {
        this.claims.add(claim);
    }
}
