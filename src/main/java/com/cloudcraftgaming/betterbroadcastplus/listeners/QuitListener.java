package com.cloudcraftgaming.betterbroadcastplus.listeners;

import com.cloudcraftgaming.betterbroadcastplus.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class QuitListener implements Listener {
    Main plugin;

    public QuitListener(Main instance) {
        plugin = instance;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onQuitBroadcastCheck(PlayerQuitEvent event) {
        //Do stuff to disable broadcasts here.
    }
}
