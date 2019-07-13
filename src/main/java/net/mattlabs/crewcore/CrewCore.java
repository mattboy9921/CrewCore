package net.mattlabs.crewcore;

import co.aikar.commands.PaperCommandManager;
import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private static CrewCore instance;
    public PaperCommandManager paperCommandManager;

    public void onEnable() {

        instance = this;

        // Register ACF
        paperCommandManager = new PaperCommandManager(this);

        // Register Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        // Register Commands with ACF
        paperCommandManager.registerCommand(new EnderCommand());
    }

    public void onDisable() {

    }

    public static CrewCore getInstance() {
        return instance;
    }
}
