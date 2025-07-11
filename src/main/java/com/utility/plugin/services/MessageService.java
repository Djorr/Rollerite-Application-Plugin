package com.utility.plugin.services;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Service class for handling plugin messages and formatting.
 * 
 * @author UtilityPlugin Team
 */
public class MessageService {
    
    private final UtilityPlugin plugin;
    private final ConfigService configService;
    
    public MessageService(UtilityPlugin plugin) {
        this.plugin = plugin;
        this.configService = plugin.getConfigService();
    }
    
    public void sendMessage(CommandSender sender, String messagePath) {
        sendMessage(sender, messagePath, new String[0]);
    }
    
    public void sendMessage(CommandSender sender, String messagePath, String... replacements) {
        String message = configService.getMessage(messagePath, replacements);
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', configService.getPrefix() + message));
    }
    
    public void sendNoPermission(CommandSender sender) {
        sendMessage(sender, "no-permission");
    }
    
    public void sendPlayerOnly(CommandSender sender) {
        sendMessage(sender, "player-only");
    }
    
    public void sendPlayerNotFound(CommandSender sender, String playerName) {
        sendMessage(sender, "player-not-found", "player", playerName);
    }
    
    public void sendConsolePlayerRequired(CommandSender sender) {
        sendMessage(sender, "console-player-required");
    }
    
    public void sendError(CommandSender sender) {
        sendMessage(sender, "error-occurred");
    }
    
    public String formatMessage(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
} 