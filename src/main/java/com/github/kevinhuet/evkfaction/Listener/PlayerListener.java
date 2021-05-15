package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Claim;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Relation;
import com.github.kevinhuet.evkfaction.Service.ConfigManager;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerPreLogin(PlayerLoginEvent event) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(event.getPlayer());

        if (factionPlayer == null) {
            factionPlayer = new FactionPlayer(event.getPlayer());
            FactionPlayerManager.getInstance().addPlayerFaction(factionPlayer);
            ConfigManager.getInstance().addPlayer(factionPlayer);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {

    }


    @EventHandler(priority=EventPriority.HIGHEST)
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent event) {
        FactionPlayer attacker = null;
        FactionPlayer target = null;
        Faction faction = null;

        if (!(event.getDamager() instanceof Player) && !(event.getEntity() instanceof Player))
            return;
        attacker = FactionPlayerManager.getInstance().getPlayerFaction((Player) event.getDamager());
        target = FactionPlayerManager.getInstance().getPlayerFaction((Player) event.getEntity());
        if (attacker == null || target == null || attacker.getFaction() == null || target.getFaction() == null)
            return;
        if (attacker.getFaction() == target.getFaction() || attacker.getFaction().checkRelation(target.getFaction()) == Relation.ALLY
            || target.getFaction().checkRelation(attacker.getFaction()) == Relation.ALLY)
            event.setCancelled(true);
        faction = FactionManager.getInstance().getFactionByChunk(attacker.getPlayer().getLocation().getChunk());
        if (!faction.getFlags().isPvp())
            event.setCancelled(true);
    }
}
