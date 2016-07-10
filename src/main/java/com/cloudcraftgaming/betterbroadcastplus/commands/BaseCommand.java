package com.cloudcraftgaming.betterbroadcastplus.commands;

import com.cloudcraftgaming.betterbroadcastplus.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * Created by Nova Fox on 5/29/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: BetterBroadcast.
 */
public class BaseCommand implements CommandExecutor {
    Main plugin;
    public BaseCommand(Main instance) {
        plugin = instance;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
        if (cmd.getName().equalsIgnoreCase("bc") || cmd.getName().equalsIgnoreCase("broadcast")) {
            String prefix = ChatColor.BLUE + "[BroadCast] ";
            if (sender.hasPermission("BB.use.broadcast")) {
                if (args.length < 1) {
                    sender.sendMessage(prefix + ChatColor.RED + "Too few arguments!");
                } else if (args.length > 0) {
                    String broadcast1 = "";
                    for (int i =0; i < args.length; i++) {
                        String arg = args[i] + " ";
                        broadcast1 = broadcast1 + arg;
                    }
                    Bukkit.broadcastMessage(prefix + ChatColor.translateAlternateColorCodes('&', broadcast1));
                }
            } else {
                sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
            }
        }
        return false;
    }
}
