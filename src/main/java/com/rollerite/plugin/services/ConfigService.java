package com.rollerite.plugin.services;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.configuration.file.FileConfiguration;

public class ConfigService {
    
    private final RolleritePlugin plugin;
    private FileConfiguration config;
    
    public ConfigService(RolleritePlugin plugin) {
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
        return getMessage("prefix");
    }
    
    public int getTPATimeout() {
        return config.getInt("settings.tpa-timeout", 60);
    }
    
    public int getTPACooldown() {
        return config.getInt("settings.tpa-cooldown", 10);
    }
    
    public boolean isGodHungerEnabled() {
        return config.getBoolean("settings.god-hunger", false);
    }
    
    public boolean isGodFallDamageEnabled() {
        return config.getBoolean("settings.god-fall-damage", false);
    }
} 