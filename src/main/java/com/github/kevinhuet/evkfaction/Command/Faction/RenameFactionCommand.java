package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class RenameFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (args.length < 2)
            return;
        if (factionPlayer == null || !factionPlayer.getRole().isAtMost(Role.OFFICER))
            return;
        factionPlayer.getFaction().rename(args[1]);
    }

    @Override
    public String getPermission() {
        return null;
    }
}
