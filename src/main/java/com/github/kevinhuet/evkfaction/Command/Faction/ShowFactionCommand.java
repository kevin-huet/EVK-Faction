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

public class ShowFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        Faction faction = null;
        FactionPlayer factionPlayer = null;

        if (args.length < 2) {
            factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);
            faction = (factionPlayer != null) ? factionPlayer.getFaction() : null;
            if (faction != null) {
                player.sendMessage(new String[]{ChatColor.GOLD+"----[ " + faction.getName() + " ]----",
                        ChatColor.GOLD+"Description: "+ChatColor.YELLOW+faction.getDescription(),
                        ChatColor.GOLD+"Age: "+ChatColor.YELLOW,
                        ChatColor.GOLD+"Land / Power / Maxpower:  "+ChatColor.YELLOW+faction.getClaims().size()+"/"+faction.getPower()+"/"+faction.getPowerMax()
                });
            }
            return;
        }
        faction = FactionManager.getInstance().getFactionByName(args[1]);
        if (faction == null)
            return;
        player.sendMessage(new String[]{ChatColor.GOLD+"----[ " + faction.getName() + " ]----",
                ChatColor.GOLD+"Description: "+ChatColor.YELLOW+faction.getDescription(),
                ChatColor.GOLD+"Age: "+ChatColor.YELLOW,
                ChatColor.GOLD+"Land / Power / Maxpower:  "+ChatColor.YELLOW+faction.getClaims().size()+"/"+faction.getPower()+"/"+faction.getPowerMax()
        });
    }

    @Override
    public String getPermission() {
        return null;
    }
}
