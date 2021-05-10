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

public class ChunkListener implements Listener {

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
        Chunk isNewChunk = player.getLocation().getChunk();
        if (player.getLocation().getChunk() != isNewChunk){
            player.sendMessage("You changed Chunk!");
        }
    }
}
