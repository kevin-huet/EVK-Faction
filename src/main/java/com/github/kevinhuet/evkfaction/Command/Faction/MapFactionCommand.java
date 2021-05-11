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

    private Collection<Chunk> getChunksAroundPlayer(Player player) {
        World world = player.getWorld();
        int[] offset = {-1, 0, 1};
        int baseX = player.getLocation().getChunk().getX();
        int baseZ = player.getLocation().getChunk().getZ();
        String[] arrays = new String[]{"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        Map<String, String>factionMap = new HashMap<>();
        int i = 0;
        int j = 0;
        char ascii = '#';
        Collection<Chunk> chunksAroundPlayer = new HashSet<>();
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

                    if (factionMap.containsKey(faction.getName()))
                        arrays[i] += ChatColor.GREEN +factionMap.get(faction.getName())+ChatColor.WHITE;
                    else {
                        arrays[i] += ChatColor.GREEN + Character.toString(ascii) + ChatColor.WHITE;
                        factionMap.put(faction.getName(), String.valueOf(ascii++));
                    }
                } else if (factionPlayer.getFaction() != faction && factionPlayer.getFaction().getRelations().get(faction.getName()) == Relation.ENEMY) {

                    if (factionMap.containsKey(faction.getName()))
                        arrays[i] += ChatColor.RED +factionMap.get(faction.getName())+ChatColor.WHITE;
                    else {
                        arrays[i] += ChatColor.RED + Character.toString(ascii) + ChatColor.WHITE;
                        factionMap.put(faction.getName(), String.valueOf(ascii++));
                    }

                } else if (factionPlayer.getFaction() != faction && factionPlayer.getFaction().getRelations().get(faction.getName()) == Relation.ALLY) {

                    if (factionMap.containsKey(faction.getName()))
                        arrays[i] += ChatColor.LIGHT_PURPLE +factionMap.get(faction.getName())+ChatColor.WHITE;
                    else {
                        arrays[i] += ChatColor.LIGHT_PURPLE + Character.toString(ascii) + ChatColor.WHITE;
                        factionMap.put(faction.getName(), String.valueOf(ascii++));
                    }

                } else if (factionPlayer.getFaction() != faction) {

                    if (factionMap.containsKey(faction.getName()))
                        arrays[i] += ChatColor.WHITE +factionMap.get(faction.getName())+ChatColor.WHITE;
                    else {
                        arrays[i] += ChatColor.WHITE + Character.toString(ascii) + ChatColor.WHITE;
                        factionMap.put(faction.getName(), String.valueOf(ascii++));
                    }
                }
                j++;
            }
            player.sendMessage(arrays[i]);
            i++;
            j = 0;
        }
        return chunksAroundPlayer;
    }


}
