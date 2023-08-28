package net.mattlabs.crewcore.commands;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

public class EnderCommand {

    PaperCommandManager<CommandSender> commandManager;
    Plugin plugin;

    public EnderCommand(PaperCommandManager<CommandSender> commandManager, Plugin plugin) {
        this.commandManager = commandManager;
        this.plugin = plugin;
        commands();
    }

    private void commands() {
        // Set up builder with permissions
        Command.Builder<CommandSender> builder = commandManager.commandBuilder("ender");

        // Base Command
        commandManager.command(builder
                .meta(CommandMeta.DESCRIPTION, "Commemorates first Ender Dragon fight on CCS.")
                .handler(this::onEnder)
        );
    }

    public void onEnder(CommandContext<CommandSender> context) {
        CrewCore.getInstance().getPlatform().sender(context.getSender()).sendMessage(Messages.ender());
    }
}
