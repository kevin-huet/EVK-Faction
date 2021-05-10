package com.github.kevinhuet.evkfaction;

import com.github.kevinhuet.evkfaction.Command.BaseCommand;
import com.github.kevinhuet.evkfaction.Service.CommandManager;
import org.bukkit.command.PluginCommand;
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
        getLogger().info("enable");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static App getInstance() {
        return instance;
    }
}
