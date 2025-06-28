package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /fix command.
 * Allows players to repair items in their main hand.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FixCommand extends BaseCommand {
    
    /**
     * Constructs a new FixCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public FixCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.fix");
    }
    
    /**
     * Executes the fix command logic.
     * 
     * @param sender The command sender (player or console)
     * @param args Command arguments
     * @return true if command executed successfully, false otherwise
     */
    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        Player target = null;
        
        // Determine target player
        if (args.length > 0) {
            // If player name provided, get that player
            target = commandUtils.getTargetPlayer(sender, args[0]);
            if (target == null) {
                return true; // Error message already sent
            }
        } else {
            // No player specified, target the sender
            if (!commandUtils.isPlayer(sender)) {
                return true; // Error message already sent
            }
            target = (Player) sender;
        }
        
        // Get the item in the target player's main hand
        ItemStack itemInHand = target.getInventory().getItemInMainHand();
        
        // Check if the player is holding an item
        if (itemInHand.getType().isAir()) {
            plugin.getMessageService().sendMessage(sender, "no-item");
            return true;
        }
        
        // Check if the item can be damaged (and therefore repaired)
        ItemMeta meta = itemInHand.getItemMeta();
        if (!(meta instanceof Damageable)) {
            plugin.getMessageService().sendMessage(sender, "no-item");
            return true;
        }
        
        Damageable damageable = (Damageable) meta;
        
        // Check if the item is already at full durability
        if (damageable.getDamage() == 0) {
            plugin.getMessageService().sendMessage(sender, "item-already-repaired");
            return true;
        }
        
        // Repair the item by setting damage to 0
        damageable.setDamage(0);
        itemInHand.setItemMeta(meta);
        
        // Update the player's inventory
        target.getInventory().setItemInMainHand(itemInHand);
        
        // Send success message
        plugin.getMessageService().sendMessage(sender, "item-repaired", "player", target.getName());
        
        return true;
    }
    
    /**
     * Provides tab completion for the fix command.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return List of tab completion options
     */
    @Override
    protected List<String> tabComplete(CommandSender sender, String[] args) {
        if (args.length == 1) {
            // Tab complete player names
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
} 