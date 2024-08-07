package net.mattlabs.crewcore;

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
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.incendo.cloud.bukkit.CloudBukkitCapabilities;
import org.incendo.cloud.execution.ExecutionCoordinator;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

public class CrewCore extends JavaPlugin {

    private boolean discordSRVEnabled;
    private static CrewCore instance;
    public LegacyPaperCommandManager<CommandSender> commandManager;
    private BukkitAudiences platform;
    private static Permission permission;
    private Config config;
    private BukkitTask discordReminder;

    public static boolean testEnabled = false;

    public void onEnable() {

        instance = this;

        // Vault Check
        if (!hasVault()) {
            this.getLogger().severe("Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // Vault Setup
        if (!setupPermissions() && !testEnabled) {
            this.getLogger().severe("Disabled due to Vault Permissions error!");
            this.getLogger().severe("Is there a permission plugin installed?");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // DiscordSRV Check
        if (!hasDiscordSRV()) {
            this.getLogger().info("DiscordSRV not detected, disabling integration.");
            discordSRVEnabled = false;
        }
        else {
            this.getLogger().info("DiscordSRV detected, enabling integration.");
            discordSRVEnabled = true;
        }

        // Configuration Section
        ConfigurateManager configurateManager = new ConfigurateManager(this);
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

        // Register Cloud
        commandManager = LegacyPaperCommandManager.createNative(
                this,
                ExecutionCoordinator.coordinatorFor(ExecutionCoordinator.nonSchedulingExecutor())
        );
        // Register Brigadier, fallback to asynchronous completions
        if (commandManager.hasCapability(CloudBukkitCapabilities.NATIVE_BRIGADIER) && !testEnabled) commandManager.registerBrigadier();
        else if (commandManager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) commandManager.registerAsynchronousCompletions();
        // Create Commands
        if (config.getEnder() || testEnabled)
            new EnderCommand(commandManager, this);
        new FCommand(commandManager, this);

        // Enable Discord Reminder
        discordReminder = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new DiscordReminder(), 0, 2400);
    }

    public void onDisable() {
        platform.close();
        discordReminder.cancel();
    }

    public static CrewCore getInstance() {
        return instance;
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

    public LegacyPaperCommandManager<CommandSender> getCommandManager() {
        return commandManager;
    }

    // Vault Helper Methods

    private boolean hasVault() {
        return getServer().getPluginManager().getPlugin("Vault") != null || testEnabled;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        if (rsp != null) {
            permission = rsp.getProvider();
            return true;
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
