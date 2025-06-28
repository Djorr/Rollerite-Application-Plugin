package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /enderchest command.
 * Allows players to open another player's ender chest.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class EnderChestCommand extends BaseCommand {
    
    /**
     * Constructs a new EnderChestCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public EnderChestCommand(UtilityPlugin plugin) {
        super(plugin, "utility.enderchest");
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
        player.openInventory(target.getEnderChest());
        
        plugin.getMessageService().sendMessage(sender, "enderchest-opened", "player", target.getName());
        plugin.getMessageService().sendMessage(target, "enderchest-opened-other", "player", player.getName());
        
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
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
} 