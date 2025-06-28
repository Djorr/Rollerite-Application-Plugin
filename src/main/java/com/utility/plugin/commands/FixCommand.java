package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /fix command.
 * Allows players to repair all items for themselves or other players.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class FixCommand extends BaseCommand {
    
    /**
     * Constructs a new FixCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public FixCommand(UtilityPlugin plugin) {
        super(plugin, "utility.fix");
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
        
        if (args.length > 0) {
            target = commandUtils.getTargetPlayer(sender, args[0]);
            if (target == null) {
                return true;
            }
        } else {
            if (!commandUtils.isPlayer(sender)) {
                return true;
            }
            target = (Player) sender;
        }
        
        // Repair all items in inventory
        repairInventory(target);
        
        if (target.equals(sender)) {
            plugin.getMessageService().sendMessage(sender, "items-fixed");
        } else {
            plugin.getMessageService().sendMessage(sender, "items-fixed-other", "player", target.getName());
        }
        
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
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
    
    /**
     * Repairs all items in a player's inventory.
     * 
     * @param player The player whose inventory to repair
     */
    private void repairInventory(Player player) {
        // Repair main inventory
        for (ItemStack item : player.getInventory().getContents()) {
            if (item != null && item.getType().getMaxDurability() > 0) {
                item.setDurability((short) 0);
            }
        }
        
        // Repair armor
        for (ItemStack item : player.getInventory().getArmorContents()) {
            if (item != null && item.getType().getMaxDurability() > 0) {
                item.setDurability((short) 0);
            }
        }
        
        // Repair offhand
        ItemStack offhand = player.getInventory().getItemInOffHand();
        if (offhand != null && offhand.getType().getMaxDurability() > 0) {
            offhand.setDurability((short) 0);
        }
    }
} 