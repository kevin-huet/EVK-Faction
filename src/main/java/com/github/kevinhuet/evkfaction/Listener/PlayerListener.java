package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.FactionType;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
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
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerLoginEvent event) {

    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Faction faction = null;
        FactionPlayer damager = null;
        FactionPlayer damagee = null;
        if (event.getEntity() instanceof Player) {
            damagee = FactionPlayerManager.getInstance().getPlayerFaction((Player) event.getEntity());
            damager = FactionPlayerManager.getInstance().getPlayerFaction((Player) event.getDamager());
            if (damagee == null || damager == null)
                return;
            if (damagee.getFaction() == damager.getFaction())
                event.setCancelled(true);
        }
    }
}
