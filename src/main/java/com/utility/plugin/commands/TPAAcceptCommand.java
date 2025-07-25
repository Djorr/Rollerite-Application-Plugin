package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /tpaccept command.
 * Allows players to accept teleport requests.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TPAAcceptCommand extends BaseCommand {
    
    /**
     * Constructs a new TPAAcceptCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TPAAcceptCommand(UtilityPlugin plugin) {
        super(plugin, "utility.tpa");
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
        if (!commandUtils.isPlayer(sender)) {
            return true;
        }
        
        Player player = (Player) sender;
        plugin.getTpaService().acceptTPARequest(player);
        
        return true;
    }
    
    /**
     * Provides tab completion for the tpaccept command.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return List of tab completion options
     */
    @Override
    protected List<String> tabComplete(CommandSender sender, String[] args) {
        return new ArrayList<>();
    }
} 