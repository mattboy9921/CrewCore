package net.mattlabs.crewcore.commands;

import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnderCommand implements CommandExecutor{


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        commandSender.spigot().sendMessage(Messages.ender());
        return true;
    }
}
