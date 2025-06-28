package com.rollerite.plugin;

import com.rollerite.plugin.commands.*;
import com.rollerite.plugin.listeners.GodModeListener;
import com.rollerite.plugin.listeners.TrashGUIListener;
import com.rollerite.plugin.services.ConfigService;
import com.rollerite.plugin.services.MessageService;
import com.rollerite.plugin.services.TPAService;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main plugin class for the Rollerite Plugin.
 * Provides various utility commands and features for Minecraft servers.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class RolleritePlugin extends JavaPlugin {
    
    // Singleton instance for easy access from other classes
    private static RolleritePlugin instance;
    
    // Service layer components
    private ConfigService configService;
    private MessageService messageService;
    private TPAService tpaService;
    
    /**
     * Called when the plugin is enabled.
     * Initializes all services, registers commands and listeners.
     */
    @Override
    public void onEnable() {
        // Set singleton instance
        instance = this;
        
        // Initialize service layer
        initializeServices();
        
        // Register commands
        registerCommands();
        
        // Register event listeners
        registerListeners();
        
        // Log successful startup
        getLogger().info("Rollerite Plugin has been enabled successfully!");
        getLogger().info("Version: " + getDescription().getVersion());
    }
    
    /**
     * Called when the plugin is disabled.
     * Performs cleanup operations.
     */
    @Override
    public void onDisable() {
        // Cleanup TPA service
        if (tpaService != null) {
            tpaService.shutdown();
        }
        
        // Clear god mode for all players
        com.rollerite.plugin.commands.GodCommand.clearAllGodMode();
        
        getLogger().info("Rollerite Plugin has been disabled!");
    }
    
    /**
     * Initializes all service layer components.
     */
    private void initializeServices() {
        this.configService = new ConfigService(this);
        this.messageService = new MessageService(this);
        this.tpaService = new TPAService(this);
    }
    
    /**
     * Registers all plugin commands with their respective executors and tab completers.
     */
    private void registerCommands() {
        // Register commands with both executor and tab completer
        GamemodeCommand gamemodeCommand = new GamemodeCommand(this);
        getCommand("gamemode").setExecutor(gamemodeCommand);
        getCommand("gamemode").setTabCompleter(gamemodeCommand);
        
        GodCommand godCommand = new GodCommand(this);
        getCommand("god").setExecutor(godCommand);
        getCommand("god").setTabCompleter(godCommand);
        
        OpenInvCommand openInvCommand = new OpenInvCommand(this);
        getCommand("openinv").setExecutor(openInvCommand);
        getCommand("openinv").setTabCompleter(openInvCommand);
        
        EnderChestCommand enderChestCommand = new EnderChestCommand(this);
        getCommand("enderchest").setExecutor(enderChestCommand);
        getCommand("enderchest").setTabCompleter(enderChestCommand);
        
        FixCommand fixCommand = new FixCommand(this);
        getCommand("fix").setExecutor(fixCommand);
        getCommand("fix").setTabCompleter(fixCommand);
        
        TPACommand tpaCommand = new TPACommand(this);
        getCommand("tpa").setExecutor(tpaCommand);
        getCommand("tpa").setTabCompleter(tpaCommand);
        
        TPAAcceptCommand tpaAcceptCommand = new TPAAcceptCommand(this);
        getCommand("tpaccept").setExecutor(tpaAcceptCommand);
        getCommand("tpaccept").setTabCompleter(tpaAcceptCommand);
        
        TPADenyCommand tpaDenyCommand = new TPADenyCommand(this);
        getCommand("tpadeny").setExecutor(tpaDenyCommand);
        getCommand("tpadeny").setTabCompleter(tpaDenyCommand);
        
        TrashCommand trashCommand = new TrashCommand(this);
        getCommand("trash").setExecutor(trashCommand);
        getCommand("trash").setTabCompleter(trashCommand);
    }
    
    /**
     * Registers all event listeners.
     */
    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new GodModeListener(this), this);
        getServer().getPluginManager().registerEvents(new TrashGUIListener(this), this);
    }
    
    /**
     * Gets the singleton instance of this plugin.
     * 
     * @return The plugin instance
     */
    public static RolleritePlugin getInstance() {
        return instance;
    }
    
    /**
     * Gets the configuration service.
     * 
     * @return The config service
     */
    public ConfigService getConfigService() {
        return configService;
    }
    
    /**
     * Gets the message service.
     * 
     * @return The message service
     */
    public MessageService getMessageService() {
        return messageService;
    }
    
    /**
     * Gets the TPA service.
     * 
     * @return The TPA service
     */
    public TPAService getTpaService() {
        return tpaService;
    }
} 