package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class ChunkListener implements Listener {

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        FactionPlayer player = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());

        if (FactionManager.getInstance().getFactionByChunk(event.getPlayer().getLocation().getChunk()) != player.getFaction())
            event.setCancelled(true);
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        FactionPlayer player = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());
        if (FactionManager.getInstance().getFactionByChunk(event.getPlayer().getLocation().getChunk()) != player.getFaction())
            event.setCancelled(true);
    }

}
