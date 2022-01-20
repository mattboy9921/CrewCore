package net.mattlabs.crewcore;

import co.aikar.commands.PaperCommandManager;
import io.leangen.geantyref.TypeToken;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.mattlabs.crewcore.commands.EnderCommand;
import net.mattlabs.crewcore.commands.FCommand;
import net.mattlabs.crewcore.listeners.JoinListener;
import net.mattlabs.crewcore.listeners.QuitListener;
import net.mattlabs.crewcore.util.ConfigurateManager;
import net.mattlabs.crewcore.util.DiscordReminder;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class CrewCore extends JavaPlugin {

    private boolean discordSRVEnabled;
    private static CrewCore instance;
    public PaperCommandManager paperCommandManager;
    private ConfigurateManager configurateManager;
    private BukkitAudiences platform;
    private static Permission permission;
    private Config config;
    private BukkitTask discordReminder;

    public void onEnable() {

        instance = this;

        // Vault Check
        if (!hasVault()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Vault Setup
        if (!setupPermissions()) {
            this.getLogger().severe("Disabled due to Vault Permissions error!");
            this.getLogger().severe("Is there a permission plugin installed?");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

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

        config = configurateManager.get("config.conf");

        // Register Audience
        platform = BukkitAudiences.create(this);

        // Register Listeners
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);

        // Register Commands with ACF
        if (enderEnabled()) paperCommandManager.registerCommand(new EnderCommand());
        paperCommandManager.registerCommand(new FCommand());

        // Enable Discord Reminder
        discordReminder = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new DiscordReminder(), 0, 2400);
    }

    public void onDisable() {
        discordReminder.cancel();
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

    public static Permission getPermission() {
        return permission;
    }

    public Config getConfigCC() {
        return config;
    }

    // Vault Helper Methods

    private boolean hasVault() {
        return getServer().getPluginManager().getPlugin("Vault") != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp != null) {
            permission = rsp.getProvider();
            return permission != null;
        }
        else return false;
    }

    // DiscordSRV Helper Method
    private boolean hasDiscordSRV() {
        return getServer().getPluginManager().getPlugin("DiscordSRV") != null;
    }

    public boolean getDiscordSRVEnabled() {
        return discordSRVEnabled;
    }
}
