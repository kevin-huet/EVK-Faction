package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerPreLogin(PlayerLoginEvent event) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());

        if (factionPlayer == null) {
            factionPlayer = new FactionPlayer(event.getPlayer());
            FactionPlayerManager.getInstance().addPlayerFaction(factionPlayer);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {

    }
}
