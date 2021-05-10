package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class FactionPlayerManager {

    private static FactionPlayerManager instance = null;
    private List<FactionPlayer> factionPlayers = new ArrayList<>();

    public FactionPlayerManager() {

    }

    public static FactionPlayerManager getInstance() {
        if (instance == null)
            instance = new FactionPlayerManager();
        return instance;
    }

    public FactionPlayer getPlayerFaction(Player player) {
        for (FactionPlayer factionPlayer : this.factionPlayers) {
            if (factionPlayer.getPlayer().getUniqueId() == player.getUniqueId())
                return factionPlayer;
        }
        return null;
    }

    public void addPlayerFaction(FactionPlayer factionPlayer) {
        this.factionPlayers.add(factionPlayer);
    }
}
