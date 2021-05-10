package com.github.kevinhuet.evkfaction.Command.Faction;

import com.github.kevinhuet.evkfaction.Command.SubCommand;
import com.github.kevinhuet.evkfaction.Entity.Faction;
import com.github.kevinhuet.evkfaction.Service.FactionManager;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

import java.util.*;

public class MapFactionCommand implements SubCommand {

    @Override
    public void onCommand(Player player, Command command, String[] args) {
        Faction faction = null;
        List<String> list = Arrays.asList(
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "------------+------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------",
                "-------------------------"
        );

        for (Chunk chunk : getChunksAroundPlayer(player)) {
            list.get()
        }
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

        Collection<Chunk> chunksAroundPlayer = new HashSet<>();

        for(int x : offset) {
            for(int z : offset) {
                Chunk chunk = world.getChunkAt(baseX + x, baseZ + z);
                chunksAroundPlayer.add(chunk);
            }
        }
        return chunksAroundPlayer;
    }


}
