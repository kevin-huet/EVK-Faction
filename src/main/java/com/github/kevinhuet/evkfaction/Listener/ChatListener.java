package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        FactionPlayer fplayer = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());

        if (fplayer != null && fplayer.hasFaction())
            event.setFormat(fplayer.getFaction().getName() + "%s: %s");
    }
}
