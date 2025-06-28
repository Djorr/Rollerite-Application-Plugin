package com.rollerite.plugin.utils;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandUtils {
    
    private final RolleritePlugin plugin;
    
    public CommandUtils(RolleritePlugin plugin) {
        this.plugin = plugin;
    }
    
    public boolean hasPermission(CommandSender sender, String permission) {
        if (!sender.hasPermission(permission)) {
            plugin.getMessageService().sendNoPermission(sender);
            return false;
        }
        return true;
    }
    
    public boolean isPlayer(CommandSender sender) {
        if (!(sender instanceof Player)) {
            plugin.getMessageService().sendPlayerOnly(sender);
            return false;
        }
        return true;
    }
    
    public Player getTargetPlayer(CommandSender sender, String playerName) {
        if (playerName == null) {
            if (!(sender instanceof Player)) {
                plugin.getMessageService().sendConsolePlayerRequired(sender);
                return null;
            }
            return (Player) sender;
        }
        
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            plugin.getMessageService().sendPlayerNotFound(sender, playerName);
            return null;
        }
        
        return target;
    }
    
    public boolean validatePlayerTarget(CommandSender sender, String playerName) {
        if (playerName == null) {
            return isPlayer(sender);
        }
        
        Player target = Bukkit.getPlayer(playerName);
        if (target == null) {
            plugin.getMessageService().sendPlayerNotFound(sender, playerName);
            return false;
        }
        
        return true;
    }
} 