package com.utility.plugin.listeners;

import com.utility.plugin.UtilityPlugin;
import com.utility.plugin.gui.TrashGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Event listener for trash GUI functionality.
 * Handles item deletion and GUI interactions.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrashGUIListener implements Listener {
    
    private final UtilityPlugin plugin;
    
    /**
     * Constructs a new TrashGUIListener instance.
     * 
     * @param plugin The main plugin instance
     */
    public TrashGUIListener(UtilityPlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Handles inventory clicks in the trash GUI.
     * Allows players to place items in the trash.
     * 
     * @param event The inventory click event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        
        // Check if this is a trash GUI
        if (!isTrashGUI(inventory)) {
            return;
        }
        
        // Allow all interactions in trash GUI
        // Items will be deleted when the GUI is closed
        event.setCancelled(false);
    }
    
    /**
     * Handles inventory close events for the trash GUI.
     * Deletes all items in the trash when closed.
     * 
     * @param event The inventory close event
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        
        // Check if this is a trash GUI
        if (!isTrashGUI(inventory)) {
            return;
        }
        
        Player player = (Player) event.getPlayer();
        
        // Count items that will be deleted
        int deletedItems = 0;
        for (ItemStack item : inventory.getContents()) {
            if (item != null && !item.getType().isAir() && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                deletedItems++;
            }
        }
        
        // Clear all items from the trash
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && !item.getType().isAir() && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                inventory.setItem(i, null);
            }
        }
        
        // Send message to player about deleted items
        if (deletedItems > 0) {
            plugin.getMessageService().sendMessage(player, "trash-item-deleted");
        }
        
        plugin.getMessageService().sendMessage(player, "trash-closed");
    }
    
    /**
     * Checks if an inventory is a trash GUI.
     * 
     * @param inventory The inventory to check
     * @return true if it's a trash GUI
     */
    private boolean isTrashGUI(Inventory inventory) {
        // Check by size and holder type instead of title
        return inventory.getSize() == 54 && inventory.getHolder() == null;
    }
} 