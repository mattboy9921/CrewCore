package net.mattlabs.crewcore.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    private final BukkitAudiences platform = CrewCore.getInstance().getPlatform();

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        if (player.hasPermission("crewcore.admin"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("crewcore.moderator"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("crewcore.member"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("crewcore.nonmember")) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.greyQuit(player.getName()));
            if (CrewCore.getInstance().getDiscordSRVEnabled())
                DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(),
                        ":heavy_multiplication_x: **" + player.getName() +  " left the server**");
        }
    }
}
