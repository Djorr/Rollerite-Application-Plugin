package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /tpa command.
 * Allows players to send teleport requests to other players.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TPACommand extends BaseCommand {
    
    /**
     * Constructs a new TPACommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TPACommand(UtilityPlugin plugin) {
        super(plugin, "utility.tpa");
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
        
        // Check if trying to TPA to self
        if (target.equals(player)) {
            plugin.getMessageService().sendMessage(sender, "tpa-self");
            return true;
        }
        
        // Send TPA request
        boolean success = plugin.getTpaService().sendTPARequest(player, target);
        
        if (success) {
            plugin.getMessageService().sendMessage(sender, "tpa-sent", "player", target.getName());
            plugin.getMessageService().sendMessage(target, "tpa-received", "player", player.getName());
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
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
} 