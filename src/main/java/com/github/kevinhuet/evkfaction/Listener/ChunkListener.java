package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.FactionType;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.HashMap;
import java.util.Map;

public class ChunkListener implements Listener {

    private Map<Player, Chunk> playerChunkMap = new HashMap<>();

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        FactionPlayer player = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());
        Faction faction = FactionManager.getInstance().getFactionByChunk(event.getBlock().getChunk());

        if (faction != null && faction != player.getFaction() && faction.getFactionType() != FactionType.FREEZONE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED+"You cannot break a block in the territory of the Bob faction");
        }
    }

    @EventHandler
    public void onPlayerPlaceBlock(BlockPlaceEvent event) {
        FactionPlayer player = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());
        Faction faction = FactionManager.getInstance().getFactionByChunk(event.getBlock().getChunk());

        if (faction != null && faction != player.getFaction() && faction.getFactionType() != FactionType.FREEZONE) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED+"You cannot place a block in the territory of the Bob faction");
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
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

    @EventHandler
    public void onPlayerChangeChunk(PlayerMoveEvent event){
        Player player = event.getPlayer();
        Faction faction = null;
        Faction lastFaction = null;
        if (event.getFrom().getChunk().equals(event.getTo().getChunk())) {

        } else {
            faction = FactionManager.getInstance().getFactionByChunk(event.getTo().getChunk());
            lastFaction = FactionManager.getInstance().getFactionByChunk(event.getFrom().getChunk());
            if (faction != null) {
                player.sendMessage(faction.getName() + " - " + faction.getDescription());
                player.sendTitle(faction.getName(), faction.getDescription());
            } else if (faction != lastFaction) {
                player.sendMessage("FreeZone - you can claim this");
                player.sendTitle("FreeZone", "you can claim this");
            }
        }
    }
}
