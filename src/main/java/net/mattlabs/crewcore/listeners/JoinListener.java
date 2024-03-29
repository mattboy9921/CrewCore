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
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private final BukkitAudiences platform = CrewCore.getInstance().getPlatform();

    public JoinListener() {

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("crewcore.admin"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.mattJoin(player.getName()));
        else if (player.hasPermission("crewcore.moderator"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.moderatorJoin(player.getName()));
        else if (player.hasPermission("crewcore.member"))
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.memberJoin(player.getName()));
        else if (player.hasPermission("crewcore.nonmember")) {
            for (Player onlinePlayer : Bukkit.getOnlinePlayers())
                platform.player(onlinePlayer).sendMessage(Messages.greyJoin(player.getName()));
            platform.player(player).sendMessage(Messages.greyInfo(player.getName()));
            if (CrewCore.getInstance().getDiscordSRVEnabled())
                DiscordUtil.sendMessage(DiscordSRV.getPlugin().getMainTextChannel(),
                        ":grey_exclamation: **" + player.getName() +  " joined the server**");
        }
    }
}
