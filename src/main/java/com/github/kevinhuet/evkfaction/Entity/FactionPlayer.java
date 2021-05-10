package com.github.kevinhuet.evkfaction.Entity;

import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class FactionPlayer {

    private UUID playerUUID;
    private Player player;
    private Faction faction = null;
    private double power = 0.0;
    private Role role = null;

    public FactionPlayer(UUID playerUUID) {
        this.playerUUID = playerUUID;
        this.player = Bukkit.getPlayer(playerUUID);
        FactionPlayerManager.getInstance().addPlayerFaction(this);
    }

    public FactionPlayer(Player player) {
        this.player = player;
        this.playerUUID = player.getUniqueId();
        FactionPlayerManager.getInstance().addPlayerFaction(this);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }

    public void setPlayerUUID(UUID playerUUID) {
        this.playerUUID = playerUUID;
    }

    public boolean hasFaction() {
        return (this.getFaction() != null && this.getFaction().getFactionType() == FactionType.NORMAL);
    }
}
