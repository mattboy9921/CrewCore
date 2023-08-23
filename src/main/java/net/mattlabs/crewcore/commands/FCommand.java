package net.mattlabs.crewcore.commands;

import cloud.commandframework.Command;
import cloud.commandframework.context.CommandContext;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import net.kyori.adventure.title.Title;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.Duration;
import java.util.Random;

public class FCommand {

    PaperCommandManager<CommandSender> commandManager;
    Plugin plugin;

    public FCommand(PaperCommandManager<CommandSender> commandManager, Plugin plugin) {
        this.commandManager = commandManager;
        this.plugin = plugin;
        commands();
    }

    private void commands() {
        // Set up builder with permissions
        Command.Builder<CommandSender> skipNightBuilder = commandManager.commandBuilder("f")
                .senderType(Player.class);

        // Base Command
        commandManager.command(skipNightBuilder
                .meta(CommandMeta.DESCRIPTION, "Allows player to pay respects.")
                .handler(context -> commandManager.taskRecipe().begin(context).asynchronous(this::onF).execute())
        );
    }

    public void onF(CommandContext<CommandSender> context) {
        // Generate random number - 1% chance for title message on command
        Title.Times times = Title.Times.times(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1));
        Title title = Title.title(Messages.canIGetAnF(), Messages.inTheChatBois(), times);
        Random random = new Random();
        if (random.nextInt(20) == 6) {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                CrewCore.getInstance().getPlatform().player(player).showTitle(title);
        }
        else {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                CrewCore.getInstance().getPlatform().player(player).sendActionBar(Messages.pressedFToPayRespects(context.getSender().getName()));
        }
    }
}
