package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Entity.Relation;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.*;

public class MapFactionCommand implements SubCommand {

    @Override
    public void onCommand(Player player, Command command, String[] args) {
        Faction faction = null;

        getChunksAroundPlayer(player);
    }

    @Override
    public String getPermission() {
        return null;
    }

    private void getChunksAroundPlayer(Player player) {
        World world = player.getWorld();
        int baseX = player.getLocation().getChunk().getX();
        int baseZ = player.getLocation().getChunk().getZ();
        String[] arrays = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        Map<String, String>factionMap = new HashMap<>();
        int i = 0;
        char ascii = '#';
        FactionPlayer factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(player);

        for (int x = baseX - 7; x < baseX + 7; x++) {
            for (int z = baseZ - 12; z < baseZ + 12; z++) {
                Chunk chunk = world.getChunkAt(x, z);
                Faction faction = FactionManager.getInstance().getFactionByChunk(chunk);
                if (x == baseX && z == baseZ) {
                    arrays[i] += ChatColor.AQUA+"+"+ChatColor.WHITE;
                } else if (faction == null) {
                    arrays[i] += "-";
                } else if (factionPlayer.getFaction() == faction) {
                    this.addClaimIntoMap(faction, factionMap, ascii, arrays, i, ChatColor.GREEN);
                } else if (factionPlayer.getFaction() != faction && faction.checkRelation(factionPlayer.getFaction()) == Relation.ENEMY) {
                    this.addClaimIntoMap(faction, factionMap, ascii, arrays, i, ChatColor.RED);
                } else if (factionPlayer.getFaction() != faction && faction.checkRelation(factionPlayer.getFaction()) == Relation.ALLY) {
                    this.addClaimIntoMap(faction, factionMap, ascii, arrays, i, ChatColor.LIGHT_PURPLE);
                } else if (factionPlayer.getFaction() != faction) {
                    this.addClaimIntoMap(faction, factionMap, ascii, arrays, i, ChatColor.WHITE);
                }
            }
            player.sendMessage(arrays[i]);
            i++;
        }
    }

    private void addClaimIntoMap(Faction faction, Map<String, String> factionMap, char ascii, String[] arrays, int i, ChatColor color) {
        if (factionMap.containsKey(faction.getName()))
            arrays[i] += color + factionMap.get(faction.getName())+ChatColor.WHITE;
        else {
            arrays[i] += color +  Character.toString(ascii) + ChatColor.WHITE;
            factionMap.put(faction.getName(), String.valueOf(ascii++));
        }
    }

}
