package net.mattlabs.crewcore;

import co.aikar.commands.PaperCommandManager;
import io.leangen.geantyref.TypeToken;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.commands.FCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import net.mattlabs.crewcore.util.ConfigurateManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CrewCore extends JavaPlugin {

    private boolean discordSRVEnabled;
    private static CrewCore instance;
    public PaperCommandManager paperCommandManager;
    private ConfigurateManager configurateManager;
    private BukkitAudiences platform;

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
        configurateManager = new ConfigurateManager();
        configurateManager.add("config.conf", TypeToken.get(Config.class), new Config(), Config::new);
        configurateManager.saveDefaults("config.conf");
        configurateManager.load("config.conf");
        configurateManager.save("config.conf");

        // Register Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        // Register Commands with ACF
        if (enderEnabled()) paperCommandManager.registerCommand(new EnderCommand());
        paperCommandManager.registerCommand(new FCommand());

        // Register Audience (Messages)
        platform = BukkitAudiences.create(this);
    }

    public void onDisable() {

    }

    public static CrewCore getInstance() {
        return instance;
    }

    private boolean enderEnabled() {
        Config config = configurateManager.get("config.conf");
        return config.getEnder();
    }

    public BukkitAudiences getPlatform() {
        return platform;
    }

    // DiscordSRV Helper Method
    private boolean hasDiscordSRV() {
        return getServer().getPluginManager().getPlugin("DiscordSRV") != null;
    }

    public boolean getDiscordSRVEnabled() {
        return discordSRVEnabled;
    }
}
