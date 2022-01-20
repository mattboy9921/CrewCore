package net.mattlabs.crewcore.util;

import github.scarsz.discordsrv.DiscordSRV;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class DiscordReminder implements Runnable {

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            boolean ignore = false;
            for (String group : CrewCore.getInstance().getConfigCC().getDiscordReminderIgnoredGroups())
                if (CrewCore.getPermission().playerInGroup(player, group)) ignore = true;

            if (!ignore && DiscordSRV.getPlugin().getAccountLinkManager().getDiscordId(player.getUniqueId()) == null)
                CrewCore.getInstance().getPlatform().player(player).sendMessage(Messages.discordReminder());
        }
    }
}
