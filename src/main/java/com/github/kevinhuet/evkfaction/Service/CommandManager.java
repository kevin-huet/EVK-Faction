package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.App;
import com.github.kevinhuet.evkfaction.Command.BaseCommand;
import com.github.kevinhuet.evkfaction.Command.Faction.*;

public class CommandManager {

    private static CommandManager instance = null;

    public CommandManager() {

    }

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();
        return instance;
    }

    public void initCommand() {
        BaseCommand cmd = new BaseCommand();
        App.getInstance().getCommand("f").setExecutor(cmd);
        cmd.registerCommand("ally", new AllyFactionCommand());
        cmd.registerCommand("claim", new ClaimFactionCommand());
        cmd.registerCommand("create", new CreateFactionCommand());
        cmd.registerCommand("demote", new DemoteFactionCommand());
        cmd.registerCommand("desc", new DescFactionCommand());
        cmd.registerCommand("disband", new DisbandFactionCommand());
        cmd.registerCommand("enemy", new EnemyFactionCommand());
        cmd.registerCommand("home", new HomeFactionCommand());
        cmd.registerCommand("invite", new InviteFactionCommand());
        cmd.registerCommand("kick", new KickFactionCommand());
        cmd.registerCommand("lead", new LeadFactionCommand());
        cmd.registerCommand("owner", new OwnerFactionCommand());
        cmd.registerCommand("promote", new PromoteFactionCommand());
        cmd.registerCommand("rename", new RenameFactionCommand());
        cmd.registerCommand("sethome", new setHomeFactionCommand());
        cmd.registerCommand("show", new ShowFactionCommand());

    }
}
