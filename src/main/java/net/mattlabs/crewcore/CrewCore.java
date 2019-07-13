package net.mattlabs.crewcore;

import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.listeners.DiscordSRVListener;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private EnderCommand enderCommand;

    private DiscordSRVListener discordSRVListener;
    private boolean discordSRVEnabled;
    private static CrewCore instance;

    public void onEnable() {

        instance = this;

        // DiscordSRV Check
        if (!hasDiscordSRV()) {
            this.getLogger().info(String.format("DiscordSRV not detected, disabling integration."));
            discordSRVEnabled = false;
        }
        else {
            this.getLogger().info(String.format("DiscordSRV detected, enabling integration."));
            discordSRVEnabled = true;
        }

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

    // DiscordSRV Helper Method
    private boolean hasDiscordSRV() {
        return getServer().getPluginManager().getPlugin("DiscordSRV") != null;
    }

    public boolean getDiscordSRVEnabled() {
        return discordSRVEnabled;
    }
}
