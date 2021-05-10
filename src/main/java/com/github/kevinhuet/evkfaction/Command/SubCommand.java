package com.github.kevinhuet.evkfaction.Command;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public interface SubCommand {
    void onCommand(Player player, Command command, String[] args);
    String getPermission();
}
