package com.rollerite.plugin.listeners;

import com.rollerite.plugin.RolleritePlugin;
import com.rollerite.plugin.gui.TrashGUI;
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
 * Handles inventory interactions and cleanup when the trash GUI is closed.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrashGUIListener implements Listener {
    
    private final RolleritePlugin plugin;
    
    /**
     * Constructs a new TrashGUIListener instance.
     * 
     * @param plugin The main plugin instance
     */
    public TrashGUIListener(RolleritePlugin plugin) {
        this.plugin = plugin;
    }
    
    /**
     * Handles inventory clicks in the trash GUI.
     * Prevents removal of border items and allows item placement.
     * 
     * @param event The inventory click event
     */
    @EventHandler(priority = EventPriority.HIGH)
    public void onInventoryClick(InventoryClickEvent event) {
        // Check if the inventory is a trash GUI
        if (!isTrashGUI(event.getInventory())) {
            return;
        }
        
        // Check if the clicker is a player
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getWhoClicked();
        
        // Prevent removing border items
        ItemStack clickedItem = event.getCurrentItem();
        if (clickedItem != null && clickedItem.getType() == Material.BLACK_STAINED_GLASS_PANE) {
            event.setCancelled(true);
            return;
        }
        
        // Allow all other interactions (placing items, taking items, etc.)
        // Items will be deleted when the GUI is closed
    }
    
    /**
     * Handles inventory close events for the trash GUI.
     * Deletes all items in the trash when it's closed and notifies the player.
     * 
     * @param event The inventory close event
     */
    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        // Check if the inventory is a trash GUI
        if (!isTrashGUI(event.getInventory())) {
            return;
        }
        
        // Check if the closer is a player
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        
        Player player = (Player) event.getPlayer();
        
        // Count items before clearing
        int itemCount = countTrashItems(event.getInventory());
        
        // Clear all items from the trash GUI
        clearTrashItems(event.getInventory());
        
        // Send message to player about items being deleted
        if (itemCount > 0) {
            try {
                plugin.getMessageService().sendMessage(player, "trash-items-deleted", "count", String.valueOf(itemCount));
            } catch (Exception e) {
                // Fallback message if config message is not found
                String fallbackMessage = plugin.getConfigService().getPrefix() + 
                    "§c" + itemCount + " items have been permanently deleted from the trash!";
                player.sendMessage(fallbackMessage);
            }
        }
        
        // Log the cleanup for debugging (optional)
        plugin.getLogger().fine("Cleared " + itemCount + " trash items for player " + player.getName());
    }
    
    /**
     * Checks if the given inventory is a trash GUI.
     * 
     * @param inventory The inventory to check
     * @return true if it's a trash GUI, false otherwise
     */
    private boolean isTrashGUI(Inventory inventory) {
        // Check if the inventory holder is null (custom inventory) and size matches
        return inventory.getHolder() == null && inventory.getSize() == 54;
    }
    
    /**
     * Counts the number of items in the trash inventory (excluding border items).
     * 
     * @param inventory The inventory to count items in
     * @return The number of items
     */
    private int countTrashItems(Inventory inventory) {
        int count = 0;
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && !item.getType().isAir() && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                count += item.getAmount();
            }
        }
        return count;
    }
    
    /**
     * Clears all items from the trash inventory, preserving border items.
     * 
     * @param inventory The inventory to clear
     */
    private void clearTrashItems(Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && !item.getType().isAir() && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                inventory.setItem(i, null);
            }
        }
    }
} 