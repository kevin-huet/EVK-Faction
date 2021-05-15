package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class LeaveFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (factionPlayer == null || factionPlayer.getFaction() == null)
            return;
        player.sendMessage("You leave faction "+factionPlayer.getFaction().getName());
        factionPlayer.getFaction().getPlayers().remove(factionPlayer);
        factionPlayer.setFaction(null);
    }

    @Override
    public String getPermission() {
        return null;
    }
}
