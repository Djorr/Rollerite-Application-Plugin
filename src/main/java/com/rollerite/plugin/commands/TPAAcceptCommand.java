package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /tpaccept command.
 * Allows players to accept incoming teleport requests.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TPAAcceptCommand extends BaseCommand {
    
    /**
     * Constructs a new TPAAcceptCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TPAAcceptCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.tpa");
    }
    
    /**
     * Executes the tpaccept command logic.
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
        
        Player player = (Player) sender;
        
        // Accept the teleport request
        boolean success = plugin.getTpaService().acceptTPARequest(player);
        
        // Always return true to prevent command usage from showing
        return true;
    }
    
    /**
     * Provides tab completion for the tpaccept command.
     * This command has no arguments, so returns empty list.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return Empty list since no arguments are expected
     */
    @Override
    protected List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
} 