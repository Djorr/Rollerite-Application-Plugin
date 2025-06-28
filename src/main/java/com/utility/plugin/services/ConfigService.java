package com.utility.plugin.services;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Service class for handling plugin configuration.
 * 
 * @author UtilityPlugin Team
 */
public class ConfigService {
    
    private final UtilityPlugin plugin;
    private FileConfiguration config;
    
    public ConfigService(UtilityPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }
    
    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        this.config = plugin.getConfig();
    }
    
    public String getMessage(String path) {
        return config.getString("messages." + path, "Message not found: " + path);
    }
    
    public String getMessage(String path, String... replacements) {
        String message = getMessage(path);
        for (int i = 0; i < replacements.length; i += 2) {
            if (i + 1 < replacements.length) {
                message = message.replace("{" + replacements[i] + "}", replacements[i + 1]);
            }
        }
        return message;
    }
    
    public String getPrefix() {
        return config.getString("prefix", "&8[&bUtilityPlugin&8] &r");
    }
    
    public int getTPATimeout() {
        return config.getInt("tpa.timeout", 60);
    }
    
    public int getTPACooldown() {
        return config.getInt("tpa.cooldown", 30);
    }
    
    public boolean isGodHungerEnabled() {
        return config.getBoolean("god.hunger", true);
    }
    
    public boolean isGodFallDamageEnabled() {
        return config.getBoolean("god.fall-damage", true);
    }
} 