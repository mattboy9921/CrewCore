package net.mattlabs.crewcore;

import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.execution.FilteringCommandSuggestionProcessor;
import cloud.commandframework.minecraft.extras.MinecraftExceptionHandler;
import cloud.commandframework.paper.PaperCommandManager;
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

import java.util.function.Function;

public class CrewCore extends JavaPlugin {

    private boolean discordSRVEnabled;
    private static CrewCore instance;
    public cloud.commandframework.paper.PaperCommandManager<CommandSender> commandManager;
    private ConfigurateManager configurateManager;
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

        // Register Cloud
        try {
            commandManager = new cloud.commandframework.paper.PaperCommandManager<>(
                    this,
                    AsynchronousCommandExecutionCoordinator.<CommandSender>builder().build(),
                    Function.identity(),
                    Function.identity()
            );
        } catch (Exception e) {
            this.getLogger().severe("Could not enable Cloud, disabling plugin...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        // Use contains filter for suggestions
        commandManager.commandSuggestionProcessor(new FilteringCommandSuggestionProcessor<>(
                FilteringCommandSuggestionProcessor.Filter.<CommandSender>contains(true).andTrimBeforeLastSpace()
        ));
        // Register Brigadier
        if (commandManager.hasCapability(CloudBukkitCapabilities.BRIGADIER)) commandManager.registerBrigadier();
        // Register asynchronous completions
        if (commandManager.hasCapability(CloudBukkitCapabilities.ASYNCHRONOUS_COMPLETION)) commandManager.registerAsynchronousCompletions();
        // Override exception handlers
        new MinecraftExceptionHandler<CommandSender>()
                .withInvalidSyntaxHandler()
                .withInvalidSenderHandler()
                .withNoPermissionHandler()
                .withArgumentParsingHandler()
                .withCommandExecutionHandler()
                .apply(commandManager, platform::sender);
        // Create Commands
        if (config.getEnder() || testEnabled)
            new EnderCommand(commandManager, this);
        new FCommand(commandManager, this);

        // Enable Discord Reminder
        discordReminder = Bukkit.getScheduler().runTaskTimerAsynchronously(this, new DiscordReminder(), 0, 2400);
    }

    public void onDisable() {
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

    public PaperCommandManager<CommandSender> getCommandManager() {
        return commandManager;
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
