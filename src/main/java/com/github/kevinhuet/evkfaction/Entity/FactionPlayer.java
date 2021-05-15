package com.github.kevinhuet.evkfaction.Entity;

import com.github.kevinhuet.evkfaction.App;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FactionPlayer implements Serializable {

    private UUID playerUUID;
    private Player player;
    private Faction faction = null;
    private double power = 0.0;
    private Role role = Role.RECRUIT;

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
        this.playerUUID = player.getUniqueId();
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
        this.player = Bukkit.getPlayer(playerUUID);
    }

    public boolean hasFaction() {
        return (this.getFaction() != null && this.getFaction().getFactionType() == FactionType.NORMAL);
    }

    public void joinFaction(Faction faction) {
        if (faction == null) {
            player.sendMessage("faction not exist.");
            return;
        }
        if (this.faction != null) {
            player.sendMessage("You need to leave your faction before join another");
            return;
        }
        if (!faction.getInvitations().containsKey(this)) {
            player.sendMessage("You need inviation for join this faction");
            return;
        }
        if (faction.getInvitations().get(this) + 300000 < Calendar.getInstance().getTimeInMillis()) {
            player.sendMessage("Invitation has expired");
            faction.getInvitations().remove(this);
            return;
        }
        this.faction = faction;
        faction.addPlayer(this);
        faction.getInvitations().remove(this);
        player.sendMessage(ChatColor.GREEN+"You join faction : "+this.getFaction().getName());
    }
}
