package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Claim;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Role;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class ChunkFactionCommand implements SubCommand {
    @Override
    public void onCommand(Player player, Command command, String[] args) {
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        if (factionPlayer == null || !factionPlayer.getRole().isAtLeast(Role.OFFICER))
            return;
        for (Claim claim : factionPlayer.getFaction().getClaims()) {
            factionPlayer.getPlayer().sendMessage(ChatColor.GREEN+"- "+claim.getChunk().toString());

        }
    }

    @Override
    public String getPermission() {
        return null;
    }
}
