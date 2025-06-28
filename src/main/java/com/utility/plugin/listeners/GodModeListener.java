package com.utility.plugin.listeners;

import com.utility.plugin.UtilityPlugin;
import com.utility.plugin.commands.GodCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Event listener for god mode functionality.
 * Handles damage prevention, hunger prevention, and cleanup when players leave.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class GodModeListener implements Listener {
    
    private final UtilityPlugin plugin;
    
    /**
     * Constructs a new GodModeListener instance.
     * 
     * @param plugin The main plugin instance
     */
    public GodModeListener(UtilityPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Prevents damage to players with god mode enabled.
     * 
     * @param event The entity damage event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onEntityDamage(EntityDamageEvent event) {
        // Check if the damaged entity is a player
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getEntity();
        
        // Check if the player has god mode enabled
        if (GodCommand.isGodMode(player)) {
            // Cancel the damage event
            event.setCancelled(true);
            
            // Log the prevented damage for debugging (optional)
            plugin.getLogger().fine("Prevented damage to player " + player.getName() + 
                " (God Mode) - Damage type: " + event.getCause());
        }
    }
    
    /**
     * Prevents hunger loss for players with god mode enabled.
     * Only active if configured in config.yml.
     * 
     * @param event The food level change event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        // Check if the entity is a player
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getEntity();
        
        // Check if god mode hunger prevention is enabled in config
        if (!plugin.getConfigService().isGodHungerEnabled()) {
            return;
        }
        
        // Check if the player has god mode enabled
        if (GodCommand.isGodMode(player)) {
            // Cancel the food level change event
            event.setCancelled(true);
            
            // Log the prevented hunger loss for debugging (optional)
            plugin.getLogger().fine("Prevented hunger loss for player " + player.getName() + 
                " (God Mode)");
        }
    }
    
    /**
     * Cleans up god mode when a player leaves the server.
     * 
     * @param event The player quit event
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        // Remove god mode from the player when they leave
        if (GodCommand.isGodMode(player)) {
            GodCommand.removeGodMode(player);
            
            // Log the cleanup for debugging (optional)
            plugin.getLogger().fine("Removed god mode from player " + player.getName() + 
                " (Player left)");
        }
    }
} 