package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ShowFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        Faction faction = null;
        FactionPlayer factionPlayer = null;

        if (args.length < 2) {
            factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);
            faction = (factionPlayer != null) ? factionPlayer.getFaction() : null;
            if (faction != null) {
                sendMessage(player, faction, ChatColor.GREEN);
            }
            return;
        }
        faction = FactionManager.getInstance().getFactionByName(args[1]);
        if (faction == null)
            return;
        sendMessage(player, faction, ChatColor.WHITE);
    }

    @Override
    public String getPermission() {
        return null;
    }

    private void sendMessage(Player player, Faction faction, ChatColor factionRelation) {

        List<String> onlinePlayers = new ArrayList<>();
        List<String> offlinePlayers = new ArrayList<>();

        for (FactionPlayer fplayer : faction.getPlayers()) {
            if (fplayer.getPlayer().isOnline()) {
                onlinePlayers.add(fplayer.getPlayer().getName());
            } else {
                offlinePlayers.add(fplayer.getPlayer().getName());
            }
        }

        player.sendMessage(new String[]{ChatColor.GOLD+"________[ " + faction.getName() + " ]________",
                ChatColor.GOLD+"Description: "+ChatColor.YELLOW+faction.getDescription(),
                ChatColor.GOLD+"Age: "+ChatColor.YELLOW,
                ChatColor.GOLD+"Land / Power / Maxpower:  "+ChatColor.YELLOW+faction.getClaims().size()+"/"+faction.getPower()+"/"+faction.getPowerMax(),
                ChatColor.GOLD+"Members online ("+onlinePlayers.size()+"): "+factionRelation+String.join(String.valueOf(", "), onlinePlayers),
                ChatColor.GOLD+"Members offline ("+offlinePlayers.size()+"): "+factionRelation+String.join(String.valueOf(", "), offlinePlayers)
        });
    }
}
