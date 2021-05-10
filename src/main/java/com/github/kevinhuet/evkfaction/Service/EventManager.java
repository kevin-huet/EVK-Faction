package com.github.kevinhuet.evkfaction.Service;

import com.github.kevinhuet.evkfaction.App;
import com.github.kevinhuet.evkfaction.Listener.ChatListener;
import com.github.kevinhuet.evkfaction.Listener.ChunkListener;

public class EventManager {

    private static EventManager instance = null;

    public EventManager() {

    }

    public static EventManager getInstance() {
        if (instance == null)
            instance = new EventManager();
        return instance;
    }

    public void InitEvents() {
        App.getInstance().getServer().getPluginManager().registerEvents(new ChatListener(), App.getInstance());
        App.getInstance().getServer().getPluginManager().registerEvents(new ChunkListener(), App.getInstance());
    }
}
