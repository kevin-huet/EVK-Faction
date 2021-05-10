package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class ClaimFactionCommand implements SubCommand {

    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (factionPlayer == null || !factionPlayer.getRole().isAtLeast(Role.OFFICER))
            return;
        factionPlayer.getFaction().claim(factionPlayer, player.getLocation().getChunk());
    }

    @Override
    public String getPermission() {
        return null;
    }
}
