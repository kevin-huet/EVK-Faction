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

        if (factionPlayer == null || !factionPlayer.getRole().isAtMost(Role.OFFICER))
            return;
        factionPlayer.getFaction().claim(player.getLocation().getChunk());

        for (FactionPlayer fp : factionPlayer.getFaction().getPlayers()) {
            fp.getPlayer().sendMessage(ChatColor.YELLOW+factionPlayer.getPlayer().getName()+
                    ChatColor.GREEN+" claimed a land at the coordinates : "+
                    player.getLocation().getChunk().getX()+", "+player.getLocation().getChunk().getZ());
        }
    }

    @Override
    public String getPermission() {
        return null;
    }
}
