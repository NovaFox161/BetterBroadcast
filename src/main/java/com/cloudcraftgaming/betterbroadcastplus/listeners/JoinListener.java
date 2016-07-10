package com.cloudcraftgaming.betterbroadcastplus.listeners;

import com.cloudcraftgaming.betterbroadcastplus.Main;
import com.cloudcraftgaming.betterbroadcastplus.utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class JoinListener implements Listener {
    Main plugin;

    public JoinListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoinCheckBroadcasts(PlayerJoinEvent event) {
        //Do stuff for broadcasts to enable them if player only.
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoinUpdateCheck(PlayerJoinEvent event) {
        if (plugin.getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            if (player.hasPermission("BB.notify.update")) {
                plugin.updateChecker = new UpdateChecker(plugin, "http://dev.bukkit.org/bukkit-plugins/betterbroadcast-plus/files.rss");
                if (plugin.updateChecker.UpdateNeeded()) {
                    player.sendMessage("&6A new update for BetterBroadcast is available! &2Version: " + plugin.updateChecker.getVersion());
                    player.sendMessage("&6Download now at: &2" + plugin.updateChecker.getLink());
                }
            }
        }
    }
}
