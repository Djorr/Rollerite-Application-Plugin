package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /tpa command.
 * Allows players to send teleport requests to other players.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TPACommand extends BaseCommand {
    
    /**
     * Constructs a new TPACommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TPACommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.tpa");
    }
    
    /**
     * Executes the tpa command logic.
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
        
        // Send the teleport request (including to yourself)
        Player playerSender = (Player) sender;
        boolean success = plugin.getTpaService().sendTPARequest(playerSender, target);
        
        if (success) {
            // Send success message to sender
            plugin.getMessageService().sendMessage(sender, "tpa-sent", "player", target.getName());
            
            // Send notification to target player (including yourself)
            plugin.getMessageService().sendMessage(target, "tpa-received", "player", playerSender.getName());
        }
        
        return true;
    }
    
    /**
     * Provides tab completion for the tpa command.
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