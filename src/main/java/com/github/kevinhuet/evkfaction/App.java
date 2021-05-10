package com.github.kevinhuet.evkfaction;

import com.github.kevinhuet.evkfaction.Command.BaseCommand;
import com.github.kevinhuet.evkfaction.Entity.FactionPlayer;
import com.github.kevinhuet.evkfaction.Service.CommandManager;
import com.github.kevinhuet.evkfaction.Service.EventManager;
import com.github.kevinhuet.evkfaction.Service.FactionPlayerManager;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class App extends JavaPlugin {

    private static App instance;
    List<PluginCommand> commands = new ArrayList<>();

    @Override
    public void onEnable() {
        instance = this;
        CommandManager.getInstance().initCommand();
        EventManager.getInstance().InitEvents();
        initPlayers();
        getLogger().info("enable");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initPlayers() {
        FactionPlayer factionPlayer = null;

        for (Player p : this.getServer().getOnlinePlayers()) {
            factionPlayer = FactionPlayerManager.getInstance().getPlayerFaction(p);
            if (factionPlayer == null) {
                factionPlayer = new FactionPlayer(p);
                FactionPlayerManager.getInstance().addPlayerFaction(factionPlayer);
            }
        }

    }

    public static App getInstance() {
        return instance;
    }
}
