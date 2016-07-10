package com.cloudcraftgaming.betterbroadcastplus.broadcast;

import com.cloudcraftgaming.betterbroadcastplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class Broadcaster {
    static Broadcaster instance;

    private final HashMap<String, Integer> broadcastNumber = new HashMap<>();

    private Broadcaster() {}

    public static  Broadcaster getCaster() {
        if (instance == null) {
            instance = new Broadcaster();
        }
        return instance;
    }

    public void startBroadcast(String castName) {
        if (!broadcastNumber.containsKey(castName) && Main.plugin.getConfig().contains("Broadcast." + castName)) {
            broadcastNumber.put(castName, 0);
            schedule(castName);
        }
    }
    public void stopBroadcast(String castName) {
        if (broadcastNumber.containsKey(castName)) {
            broadcastNumber.remove(castName);
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Stopped broadcasting group:" + castName);
            }
        }
    }

    private void schedule(final String castName) {
        Integer delay = Main.plugin.getConfig().getInt("Broadcast." + castName + ".Delay");
        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                if (broadcastNumber.containsKey(castName)) {
                    broadcast(castName, broadcastNumber.get(castName));
                }
            }
        }, 20L * delay);
    }


    private void broadcast(String castName, Integer index) {
        if (Main.plugin.getConfig().contains("Broadcasts." + castName)) {
            List<String> casts = Main.plugin.getConfig().getStringList("Broadcasts." + castName);
            if (casts.size() > index) {
                String castToSend = casts.get(index);
                for (Player p : Bukkit.getOnlinePlayers()) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', castToSend));
                }
                if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                    Main.plugin.getLogger().info(castToSend);
                }
                broadcastNumber.remove(castName);
                broadcastNumber.put(castName, index + 1);

                //Schedule another cast.
                schedule(castName);

            } else if (casts.size() != 0 && index != 0) {
                broadcast(castName, 0);
            } else {
                if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                    Main.plugin.getLogger().warning("Could not send broadcast because group '" + castName + "' is empty!");
                }
            }
        } else {
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().warning("Could not send broadcast because group '" + castName + "' does not exist!");
            }
        }
    }
}
