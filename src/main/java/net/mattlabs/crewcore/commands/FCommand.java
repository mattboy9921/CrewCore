package net.mattlabs.crewcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

@CommandAlias("f")
public class FCommand extends BaseCommand {


    @Default
    @Description("Allows player to pay respects.")
    public void onDefault(CommandSender commandSender) {
        // Generate random number - 1% chance for title message on command
        Random random = new Random();
        if (random.nextInt(20) == 6) {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                player.sendTitle(TextComponent.toLegacyText(Messages.canIgetAnF()),
                        TextComponent.toLegacyText(Messages.inTheChatBois()),
                        20, 60, 20);
        }
        else {
            for (Player player : CrewCore.getInstance().getServer().getOnlinePlayers())
                player.spigot().sendMessage(ChatMessageType.ACTION_BAR, Messages.pressedFToPayRespects(commandSender.getName()));
        }
    }
}
