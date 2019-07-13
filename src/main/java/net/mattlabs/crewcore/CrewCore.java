package net.mattlabs.crewcore;

import co.aikar.commands.PaperCommandManager;
import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import net.mattlabs.crewcore.util.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private static CrewCore instance;
    public PaperCommandManager paperCommandManager;
    private ConfigManager configManager;

    public void onEnable() {

        instance = this;

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
}
