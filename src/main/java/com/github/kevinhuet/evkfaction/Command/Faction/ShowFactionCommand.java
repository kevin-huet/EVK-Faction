package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class ShowFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        Faction faction = null;

        if (args.length < 2)
            return;
        faction = FactionManager.getInstance().getFactionByName(args[1]);

    }

    @Override
    public String getPermission() {
        return null;
    }
}
