package com.utility.plugin;

import com.utility.plugin.commands.*;
import com.utility.plugin.listeners.GodModeListener;
import com.utility.plugin.listeners.TrashGUIListener;
import com.utility.plugin.services.ConfigService;
import com.utility.plugin.services.MessageService;
import com.utility.plugin.services.TPAService;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for the UtilityPlugin.
 * 
 * This class serves as the entry point for the plugin and manages
 * all core services, commands, and listeners.
 * 
 * @author UtilityPlugin Team
 */
public class UtilityPlugin extends JavaPlugin {
    
    private static UtilityPlugin instance;
    private ConfigService configService;
    private MessageService messageService;
    private TPAService tpaService;
    
    @Override
    public void onEnable() {
        // Set instance for static access
        instance = this;
        
        // Initialize services
        initializeServices();
        
        // Register commands
        registerCommands();
        
        // Register listeners
        registerListeners();
        
        // Log successful startup
        getLogger().info("UtilityPlugin has been enabled successfully!");
    }
    
    @Override
    public void onDisable() {
        // Clear all god mode players
        com.utility.plugin.commands.GodCommand.clearAllGodMode();
        
        // Log shutdown
        getLogger().info("UtilityPlugin has been disabled!");
    }
    
    /**
     * Initialize all plugin services.
     */
    private void initializeServices() {
        configService = new ConfigService(this);
        messageService = new MessageService(this);
        tpaService = new TPAService(this);
    }
    
    /**
     * Register all plugin commands.
     */
    private void registerCommands() {
        // Register command executors
        getCommand("gamemode").setExecutor(new GamemodeCommand(this));
        getCommand("god").setExecutor(new GodCommand(this));
        getCommand("openinv").setExecutor(new OpenInvCommand(this));
        getCommand("enderchest").setExecutor(new EnderChestCommand(this));
        getCommand("fix").setExecutor(new FixCommand(this));
        getCommand("tpa").setExecutor(new TPACommand(this));
        getCommand("tpaccept").setExecutor(new TPAAcceptCommand(this));
        getCommand("tpadeny").setExecutor(new TPADenyCommand(this));
        getCommand("trash").setExecutor(new TrashCommand(this));
        
        // Register tab completers
        getCommand("gamemode").setTabCompleter(new GamemodeCommand(this));
        getCommand("god").setTabCompleter(new GodCommand(this));
        getCommand("openinv").setTabCompleter(new OpenInvCommand(this));
        getCommand("enderchest").setTabCompleter(new EnderChestCommand(this));
        getCommand("fix").setTabCompleter(new FixCommand(this));
        getCommand("tpa").setTabCompleter(new TPACommand(this));
    }
    
    /**
     * Register all plugin listeners.
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new GodModeListener(this), this);
        getServer().getPluginManager().registerEvents(new TrashGUIListener(this), this);
    }
    
    /**
     * Get the ConfigService instance.
     * 
     * @return the ConfigService instance
     */
    public ConfigService getConfigService() {
        return configService;
    }
    
    /**
     * Get the MessageService instance.
     * 
     * @return the MessageService instance
     */
    public MessageService getMessageService() {
        return messageService;
    }
    
    /**
     * Get the TPAService instance.
     * 
     * @return the TPAService instance
     */
    public TPAService getTpaService() {
        return tpaService;
    }
    
    /**
     * Get the plugin instance for static access.
     * 
     * @return the plugin instance
     */
    public static UtilityPlugin getInstance() {
        return instance;
    }
} 