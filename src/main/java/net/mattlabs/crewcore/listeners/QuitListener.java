package net.mattlabs.crewcore.listeners;

import github.scarsz.discordsrv.DiscordSRV;
import github.scarsz.discordsrv.util.DiscordUtil;
import net.mattlabs.crewcore.CrewCore;
import net.mattlabs.crewcore.messaging.Messages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        event.setQuitMessage("");
        if (player.hasPermission("jl.matt"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                onlinePlayer.spigot().sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("jl.moderator"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                onlinePlayer.spigot().sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("jl.member"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                onlinePlayer.spigot().sendMessage(Messages.allQuit(player.getName()));
        else if (player.hasPermission("jl.nonmember")) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                onlinePlayer.spigot().sendMessage(Messages.greyQuit(player.getName()));
            if (CrewCore.getInstance().getDiscordSRVEnabled())
                DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(),
                        ":heavy_multiplication_x: **" + player.getName() +  " left the server**");
        }
    }
}
