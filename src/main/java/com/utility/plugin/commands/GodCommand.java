package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Command handler for the /god command.
 * Allows players to toggle god mode for themselves or other players.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class GodCommand extends BaseCommand {
    
    private static final List<UUID> godModePlayers = new ArrayList<>();
    
    /**
     * Constructs a new GodCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public GodCommand(UtilityPlugin plugin) {
        super(plugin, "utility.god");
    }
    
    /**
     * Executes the god command logic.
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
        
        boolean isGodMode = isGodMode(target);
        if (isGodMode) {
            removeGodMode(target);
            if (target.equals(sender)) {
                plugin.getMessageService().sendMessage(sender, "god-disabled");
            } else {
                plugin.getMessageService().sendMessage(sender, "god-disabled-other", "player", target.getName());
            }
        } else {
            addGodMode(target);
            if (target.equals(sender)) {
                plugin.getMessageService().sendMessage(sender, "god-enabled");
            } else {
                plugin.getMessageService().sendMessage(sender, "god-enabled-other", "player", target.getName());
            }
        }
        
        return true;
    }
    
    /**
     * Provides tab completion for the god command.
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
     * Checks if a player has god mode enabled.
     * 
     * @param player The player to check
     * @return true if the player has god mode enabled
     */
    public static boolean isGodMode(Player player) {
        return godModePlayers.contains(player.getUniqueId());
    }
    
    /**
     * Adds god mode to a player.
     * 
     * @param player The player to enable god mode for
     */
    public static void addGodMode(Player player) {
        godModePlayers.add(player.getUniqueId());
    }
    
    /**
     * Removes god mode from a player.
     * 
     * @param player The player to disable god mode for
     */
    public static void removeGodMode(Player player) {
        godModePlayers.remove(player.getUniqueId());
    }
    
    /**
     * Clears god mode from all players.
     */
    public static void clearAllGodMode() {
        godModePlayers.clear();
    }
} 