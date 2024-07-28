package net.mattlabs.crewcore.commands;

import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.incendo.cloud.Command;
import org.incendo.cloud.context.CommandContext;
import org.incendo.cloud.description.CommandDescription;
import org.incendo.cloud.paper.LegacyPaperCommandManager;

public class EnderCommand {

    LegacyPaperCommandManager<CommandSender> commandManager;
    Plugin plugin;

    public EnderCommand(LegacyPaperCommandManager<CommandSender> commandManager, Plugin plugin) {
        this.commandManager = commandManager;
        this.plugin = plugin;
        commands();
    }

    private void commands() {
        // Set up builder with permissions
        Command.Builder<CommandSender> builder = commandManager.commandBuilder("ender");

        // Base Command
        commandManager.command(builder
                .commandDescription(CommandDescription.commandDescription("Commemorates first Ender Dragon fight on CCS."))
                .handler(this::onEnder)
        );
    }

    public void onEnder(CommandContext<CommandSender> context) {
        CrewCore.getInstance().getPlatform().sender(context.sender()).sendMessage(Messages.ender());
    }
}
