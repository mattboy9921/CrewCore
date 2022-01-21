package net.mattlabs.crewcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.kyori.adventure.title.Title;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Random;

@CommandAlias("f")
public class FCommand extends BaseCommand {


    @Default
    @Description("Allows player to pay respects.")
    public void onDefault(CommandSender commandSender) {
        // Generate random number - 1% chance for title message on command
        Title.Times times = Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1));
        Title title = Title.title(Messages.canIGetAnF(), Messages.inTheChatBois(), times);
        Random random = new Random();
        if (random.nextInt(20) == 6) {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                CrewCore.getInstance().getPlatform().player(player).showTitle(title);
        }
        else {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                CrewCore.getInstance().getPlatform().player(player).sendActionBar(Messages.pressedFToPayRespects(commandSender.getName()));
        }
    }
}
