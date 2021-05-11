package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class PowerFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = null;

        if (args.length < 2)
            return;
        factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(Bukkit.getPlayer(args[1]));
        if (factionPlayer == null)
            return;
        player.sendMessage(ChatColor.GREEN+"Power of "+factionPlayer.getPlayer().getName()+" :  "+factionPlayer.getPower());
    }

    @Override
    public String getPermission() {
        return null;
    }
}
