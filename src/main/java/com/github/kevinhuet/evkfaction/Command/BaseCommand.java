package com.github.kevinhuet.evkfaction.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class BaseCommand implements CommandExecutor {

    private Map<String, SubCommand> commands = new HashMap<>();

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(!commands.containsKey(args[0].toLowerCase())) {
            sender.sendMessage("The sub command doesn't exist");
            return false;
        }

        commands.get(args[0]).onCommand((Player) sender, command, args);
        return true;
    }

    public void registerCommand(String cmd, SubCommand subCommand) {
        commands.put(cmd, subCommand);
    }
}
