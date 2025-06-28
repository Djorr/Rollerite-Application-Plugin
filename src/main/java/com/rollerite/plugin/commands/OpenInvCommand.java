package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /openinv command.
 * Allows players to open and edit another player's inventory.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class OpenInvCommand extends BaseCommand {
    
    /**
     * Constructs a new OpenInvCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public OpenInvCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.openinv");
    }
    
    /**
     * Executes the openinv command logic.
     * 
     * @param sender The command sender (player or console)
     * @param args Command arguments
     * @return true if command executed successfully, false otherwise
     */
    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        // This command requires a player to execute (cannot be used from console)
        if (!commandUtils.isPlayer(sender)) {
            return true; // Error message already sent
        }
        
        // Check if target player is specified
        if (args.length < 1) {
            return false; // Show usage
        }
        
        // Get the target player
        Player target = commandUtils.getTargetPlayer(sender, args[0]);
        if (target == null) {
            return true; // Error message already sent
        }
        
        // Allow opening own inventory (removed the restriction)
        if (target.equals(sender)) {
            // Open own inventory
            Player playerSender = (Player) sender;
            playerSender.openInventory(playerSender.getInventory());
            plugin.getMessageService().sendMessage(sender, "inventory-opened", "player", "your own");
            return true;
        }
        
        // Open the target player's inventory
        Player playerSender = (Player) sender;
        playerSender.openInventory(target.getInventory());
        
        // Send success message
        plugin.getMessageService().sendMessage(sender, "inventory-opened", "player", target.getName());
        
        return true;
    }
    
    /**
     * Provides tab completion for the openinv command.
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