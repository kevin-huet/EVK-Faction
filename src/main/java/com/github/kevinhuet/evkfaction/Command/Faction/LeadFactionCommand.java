package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class LeadFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);
        FactionPlayer target = null;
        if (args.length < 2)
            return;
        if (factionPlayer == null || !factionPlayer.getRole().isAtMost(Role.ADMIN))
            return;
        target = factionPlayer.getFaction().getPlayers().stream().filter(p -> p.getPlayer().getName().equals(args[1])).findFirst().orElse(null);
        if (target == null)
            return;
        factionPlayer.setRole(Role.OFFICER);
        target.setRole(Role.ADMIN);
    }

    @Override
    public String getPermission() {
        return null;
    }
}
