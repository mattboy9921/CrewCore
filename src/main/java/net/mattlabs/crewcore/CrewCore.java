package net.mattlabs.crewcore;

import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private EnderCommand enderCommand;

    private static CrewCore instance;

    public void onEnable() {

        instance = this;

        // Register Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        // Register Commands
        enderCommand = new EnderCommand();
        getCommand("ender").setExecutor(enderCommand);
    }

    public void onDisable() {

    }

    public static CrewCore getInstance() {
        return instance;
    }
}
