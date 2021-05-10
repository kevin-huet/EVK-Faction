package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class KickFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (factionPlayer == null || !factionPlayer.getRole().isAtMost(Role.OFFICER))
            return;
        factionPlayer.getFaction().getPlayers().remove(
                FactionPlayerManager.getInstance().getPlayerFaction(Bukkit.getPlayer(args[1]))
        );
        FactionPlayerManager.getInstance().getPlayerFaction(Bukkit.getPlayer(args[1])).setFaction(null);
    }

    @Override
    public String getPermission() {
        return null;
    }
}
