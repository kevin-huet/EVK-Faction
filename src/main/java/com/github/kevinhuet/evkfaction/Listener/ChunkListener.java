package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.FactionType;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

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

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        FactionPlayer player = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());
        if (FactionManager.getInstance().getFactionByChunk(event.getPlayer().getLocation().getChunk()) != player.getFaction())
            event.setCancelled(true);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Faction faction = null;
        if (event.getEntity() instanceof Player) {
            faction = FactionManager.getInstance().getFactionByChunk(event.getEntity().getLocation().getChunk());
            if (faction.getFactionType() == FactionType.SAFEZONE)
                event.setCancelled(true);
        }
    }

    @EventHandler
    public void onExplosion(ExplosionPrimeEvent event) {
        Faction faction = FactionManager.getInstance().getFactionByChunk(event.getEntity().getLocation().getChunk());

        if (faction.isSpecialFaction()) {
            event.setCancelled(true);
        }
    }
}
