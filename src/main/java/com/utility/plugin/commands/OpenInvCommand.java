package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /openinv command.
 * Allows players to open another player's inventory.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class OpenInvCommand extends BaseCommand {
    
    /**
     * Constructs a new OpenInvCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public OpenInvCommand(UtilityPlugin plugin) {
        super(plugin, "utility.openinv");
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
        if (!commandUtils.isPlayer(sender)) {
            return true;
        }
        
        if (args.length < 1) {
            return false;
        }
        
        Player target = commandUtils.getTargetPlayer(sender, args[0]);
        if (target == null) {
            return true;
        }
        
        Player player = (Player) sender;
        player.openInventory(target.getInventory());
        
        plugin.getMessageService().sendMessage(sender, "inventory-opened", "player", target.getName());
        plugin.getMessageService().sendMessage(target, "inventory-opened-other", "player", player.getName());
        
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
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
} 