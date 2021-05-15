package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class InviteFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);
        FactionPlayer target = null;
        if (factionPlayer == null || factionPlayer.getFaction() == null || !factionPlayer.getRole().isAtLeast(Role.OFFICER))
            return;
        target = FactionPlayerManager.getInstance().getPlayerFaction(Bukkit.getPlayer(args[1]));
        factionPlayer.getFaction().invitePlayer(target);
    }

    @Override
    public String getPermission() {
        return null;
    }
}
