package com.rollerite.plugin.gui;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

/**
 * GUI class for the trash functionality.
 * Provides a chest-like interface where players can drop items that will be deleted when closed.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrashGUI {
    
    private static final String GUI_TITLE = "§8Trash Bin";
    private static final int GUI_SIZE = 54; // 6 rows of 9 slots
    
    private final RolleritePlugin plugin;
    private final Inventory inventory;
    
    /**
     * Constructs a new TrashGUI instance.
     * 
     * @param plugin The main plugin instance
     */
    public TrashGUI(RolleritePlugin plugin) {
        this.plugin = plugin;
        this.inventory = createInventory();
    }
    
    /**
     * Creates the trash inventory with appropriate title and size.
     * 
     * @return The created inventory
     */
    private Inventory createInventory() {
        Inventory inv = Bukkit.createInventory(null, GUI_SIZE, GUI_TITLE);
        
        // Add decorative border items (optional)
        addBorderItems(inv);
        
        return inv;
    }
    
    /**
     * Adds decorative border items to the GUI.
     * 
     * @param inventory The inventory to add border items to
     */
    private void addBorderItems(Inventory inventory) {
        ItemStack borderItem = createBorderItem();
        
        // Add border to top and bottom rows
        for (int i = 0; i < 9; i++) {
            inventory.setItem(i, borderItem); // Top row
            inventory.setItem(GUI_SIZE - 9 + i, borderItem); // Bottom row
        }
        
        // Add border to left and right columns
        for (int i = 0; i < GUI_SIZE; i += 9) {
            inventory.setItem(i, borderItem); // Left column
            inventory.setItem(i + 8, borderItem); // Right column
        }
    }
    
    /**
     * Creates a decorative border item.
     * 
     * @return The border item
     */
    private ItemStack createBorderItem() {
        ItemStack item = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("§7");
            item.setItemMeta(meta);
        }
        return item;
    }
    
    /**
     * Opens the trash GUI for the specified player.
     * 
     * @param player The player to open the GUI for
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }
    
    /**
     * Gets the inventory associated with this GUI.
     * 
     * @return The inventory
     */
    public Inventory getInventory() {
        return inventory;
    }
    
    /**
     * Clears all items from the trash GUI.
     * This method is called when the GUI is closed to delete all items.
     */
    public void clearItems() {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack item = inventory.getItem(i);
            if (item != null && !item.getType().isAir() && item.getType() != Material.BLACK_STAINED_GLASS_PANE) {
                inventory.setItem(i, null);
            }
        }
    }
} 