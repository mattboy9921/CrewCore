package net.mattlabs.crewcore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Description;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.CommandSender;

@CommandAlias("ender")
public class EnderCommand extends BaseCommand {


    @Default
    @Description("Commemorates first Ender Dragon fight on CCS.")
    public void onDefault(CommandSender commandSender) {
        CrewCore.getInstance().getPlatform().sender(commandSender).sendMessage(Messages.ender());
    }
}
