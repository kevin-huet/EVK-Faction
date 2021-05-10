package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.App;
import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CreateFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (factionPlayer == null)
            factionPlayer = new FactionPlayer(player.getUniqueId());
        if (factionPlayer.hasFaction())
            return;
        if (FactionManager.getInstance().getFactionByName(args[1]) != null)
            return;
        factionPlayer.setFaction(new Faction(args[1], factionPlayer));
        factionPlayer.setRole(Role.ADMIN);
        player.sendMessage(ChatColor.YELLOW+"Faction "+args[1]+" has been created.");
    }

    @Override
    public String getPermission() {
        return null;
    }
}
