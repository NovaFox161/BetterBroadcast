package com.cloudcraftgaming.betterbroadcastplus.utils;

import com.cloudcraftgaming.betterbroadcastplus.Main;

import java.io.File;
import java.util.List;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class FileManager {
    protected static double configVersion = 1.0;


    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!file.exists()) {
            Main.plugin.getLogger().info("Generating config.yml...");

            Main.plugin.getConfig().addDefault("DO NOT DELETE", "BetterBroadcastPlus is developed and managed by Shades161");
            Main.plugin.getConfig().addDefault("Config Version", configVersion);
            Main.plugin.getConfig().addDefault("Check for Updates", true);

            Main.plugin.getConfig().addDefault("Console.Verbose", true);

            Main.plugin.getConfig().addDefault("Broadcast.Global.Prefix", "&1[BroadCast]");
            Main.plugin.getConfig().addDefault("Broadcast.Global.Enabled", true);
            Main.plugin.getConfig().addDefault("Broadcast.Global.Delay",  180);
            Main.plugin.getConfig().addDefault("Broadcast.Global.Online-Only", true);

            List<String> globalBroadcasts = Main.plugin.getConfig().getStringList("Broadcasts.Global");
            globalBroadcasts.add("This is a default broadcast.");
            globalBroadcasts.add("&4Configure your custom broadcasts in the config.yml");
            Main.plugin.getConfig().set("Broadcasts.Global", globalBroadcasts);

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }

    public static void checkFileVersions() {
        if (Main.plugin.getConfig().getDouble("Config Version") != configVersion) {
            Main.plugin.getLogger().severe("Config.yml outdated!! Plugin will not work until file is updated!");
            Main.plugin.getLogger().severe("Please copy your settings, delete the config, and restart the server!");
            Main.plugin.getLogger().severe("Disabling plugin to prevent further issues...");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }
}
