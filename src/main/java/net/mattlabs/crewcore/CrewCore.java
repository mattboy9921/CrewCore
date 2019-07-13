package net.mattlabs.crewcore;

import co.aikar.commands.PaperCommandManager;
import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import net.mattlabs.crewcore.util.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private boolean discordSRVEnabled;
    private static CrewCore instance;
    public PaperCommandManager paperCommandManager;
    private ConfigManager configManager;

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

        // Register ACF
        paperCommandManager = new PaperCommandManager(this);

        // Configuration Section
        configManager = new ConfigManager(this);
        configManager.loadConfigFiles(
                new ConfigManager.ConfigPath(
                        "config.yml",
                        "config.yml",
                        "config.yml"));
        configManager.saveAllConfigs(false);

        // Register Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        // Register Commands with ACF
        if (enderEnabled()) paperCommandManager.registerCommand(new EnderCommand());
    }

    public void onDisable() {

    }

    public static CrewCore getInstance() {
        return instance;
    }

    private boolean enderEnabled() {
        return configManager.getFileConfig("config.yml").getBoolean("ender");
    }

    // DiscordSRV Helper Method
    private boolean hasDiscordSRV() {
        return getServer().getPluginManager().getPlugin("DiscordSRV") != null;
    }

    public boolean getDiscordSRVEnabled() {
        return discordSRVEnabled;
    }
}
