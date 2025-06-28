package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /enderchest command.
 * Allows players to open their own or another player's ender chest.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class EnderChestCommand extends BaseCommand {
    
    /**
     * Constructs a new EnderChestCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public EnderChestCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.enderchest");
    }
    
    /**
     * Executes the enderchest command logic.
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
        
        // Check if sender is a player (required to open inventory)
        if (!commandUtils.isPlayer(sender)) {
            return true; // Error message already sent
        }
        
        // Open the target player's ender chest
        Player playerSender = (Player) sender;
        playerSender.openInventory(target.getEnderChest());
        
        // Send success message
        plugin.getMessageService().sendMessage(sender, "enderchest-opened", "player", target.getName());
        
        return true;
    }
    
    /**
     * Provides tab completion for the enderchest command.
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