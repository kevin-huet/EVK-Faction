package com.github.kevinhuet.evkfaction.Listener;

import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerListener implements Listener {

    Map<Player, Player> receivesHits = new HashMap<>();
    Map<Player, Player> sendsHits = new HashMap<>();

    private Map<UUID, Long> playersInCombat = new HashMap<>();

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

    @EventHandler
    public final void onEntityDamageByEntityEvent(final EntityDamageByEntityEvent event) {
        if (event.getEntity() == null || event.getDamager() == null) {
            return;
        }

        Player damaged = null;
        Player attacker = null;

        if (event.getEntityType() == EntityType.PLAYER) {
            damaged = (Player) event.getEntity();
        }

        if (event.getDamager() instanceof Player) {
            attacker = (Player) event.getDamager();
        } else if (event.getDamager() instanceof Projectile) {
            final Projectile projectile = (Projectile) event.getDamager();
            if (projectile.getShooter() != null && projectile.getShooter() instanceof Player) {
                attacker = (Player) projectile.getShooter();
            }
        } else {
            return;
        }

        if (damaged != null && attacker != null && !damaged.getUniqueId().equals(attacker.getUniqueId())) {
            if (this.isPlayerCombatTagged(damaged)) {
                this.playersInCombat.put(damaged.getUniqueId(), Calendar.getInstance().getTimeInMillis());
            }

            if (this.isPlayerCombatTagged(attacker)) {
                this.playersInCombat.put(attacker.getUniqueId(), Calendar.getInstance().getTimeInMillis());
            }
        }
    }

    @EventHandler
    public final void onPlayerQuitEvent(final PlayerQuitEvent event) {
        this.playersInCombat.remove(event.getPlayer().getUniqueId());
    }

    public final boolean isPlayerCombatTagged(Player player) {
        if (player == null) {
            return true;
        }

        final UUID playerUUID = player.getUniqueId();
        if (this.playersInCombat.containsKey(playerUUID)) {
            final long timeStamp = this.playersInCombat.get(playerUUID);
            final long timeDif = (Calendar.getInstance().getTimeInMillis() - timeStamp) / 1000;
            if (timeDif > 30) {
                this.playersInCombat.remove(playerUUID);
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }

    }
}
