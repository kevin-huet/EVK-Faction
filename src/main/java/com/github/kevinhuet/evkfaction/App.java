package com.github.kevinhuet.evkfaction;

import com.github.kevinhuet.evkfaction.Command.BaseCommand;
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
        getLogger().info("enable");
        BaseCommand cmd = new BaseCommand();
        getCommand("cmd").setExecutor(cmd);
        //cmd.registerCommand("lol", new LolSubCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static App getInstance() {
        return instance;
    }
}
