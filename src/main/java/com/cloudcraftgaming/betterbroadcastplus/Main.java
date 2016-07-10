package com.cloudcraftgaming.betterbroadcastplus;

import com.cloudcraftgaming.betterbroadcastplus.broadcast.Broadcaster;
import com.cloudcraftgaming.betterbroadcastplus.commands.BaseCommand;
import com.cloudcraftgaming.betterbroadcastplus.listeners.JoinListener;
import com.cloudcraftgaming.betterbroadcastplus.listeners.QuitListener;
import com.cloudcraftgaming.betterbroadcastplus.utils.FileManager;
import com.cloudcraftgaming.betterbroadcastplus.utils.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class Main extends JavaPlugin {
    public static Main plugin;
    public UpdateChecker updateChecker;

    public void onEnable() {
        plugin = this;

        //Create files
        FileManager.createConfig();

        //Check file versions
        FileManager.checkFileVersions();

        //Register listeners
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);

        //Register commands
        getCommand("bc").setExecutor(new BaseCommand(this));
        getCommand("broadcast").setExecutor(new BaseCommand(this));

        //Check for updates
        checkForUpdatesStart();

        //Start broadcast timers.

        if (getConfig().contains("Broadcast.Global")) {
            if (getConfig().getString("Broadcast.Global.Enabled").equalsIgnoreCase("True")) {
                Broadcaster.getCaster().startBroadcast("Global");
            }
        }
    }
    public void onDisable() {

    }

    private void checkForUpdatesStart() {
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
                    if (getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                        getLogger().info("Checking Dev Bukkit for updates...");
                    }
                    updateChecker = new UpdateChecker(Main.plugin, "http://dev.bukkit.org/bukkit-plugins/betterbroadcast-plus/files.rss");
                    if (updateChecker.UpdateNeeded()) {
                        getLogger().info("A new update for BetterBroadcastPlus is available! Version: " + updateChecker.getVersion());
                        getLogger().info("Download now at: " + updateChecker.getLink());
                    } else {
                        if (getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                            getLogger().info("No updates found on Dev Bukkit! You have the most recent version!");
                        }
                    }
                }
            }
        }, 20L);
    }

    public void saveCustomConfig(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
