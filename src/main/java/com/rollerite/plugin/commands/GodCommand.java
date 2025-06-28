package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Command handler for the /god command.
 * Allows players to toggle god mode (invincibility) for themselves or other players.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class GodCommand extends BaseCommand {
    
    // Static set to track players with god mode enabled
    // Using UUID for better performance and to handle player name changes
    private static final Set<UUID> godModePlayers = new HashSet<>();
    
    /**
     * Constructs a new GodCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public GodCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.god");
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
        
        // Toggle god mode for the target player
        boolean isGodModeEnabled = toggleGodMode(target);
        
        // Send appropriate message
        String messageKey = isGodModeEnabled ? "god-enabled" : "god-disabled";
        plugin.getMessageService().sendMessage(sender, messageKey, "player", target.getName());
        
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
            // Tab complete player names
            return getOnlinePlayerNames(sender, args[0]);
        }
        return new ArrayList<>();
    }
    
    /**
     * Toggles god mode for the specified player.
     * 
     * @param player The player to toggle god mode for
     * @return true if god mode was enabled, false if disabled
     */
    private boolean toggleGodMode(Player player) {
        UUID playerId = player.getUniqueId();
        
        if (godModePlayers.contains(playerId)) {
            // Disable god mode
            godModePlayers.remove(playerId);
            return false;
        } else {
            // Enable god mode
            godModePlayers.add(playerId);
            return true;
        }
    }
    
    /**
     * Checks if a player has god mode enabled.
     * 
     * @param player The player to check
     * @return true if the player has god mode enabled, false otherwise
     */
    public static boolean isGodModeEnabled(Player player) {
        return godModePlayers.contains(player.getUniqueId());
    }
    
    /**
     * Removes god mode from a player.
     * Used when player logs out or plugin is disabled.
     * 
     * @param player The player to remove god mode from
     */
    public static void removeGodMode(Player player) {
        godModePlayers.remove(player.getUniqueId());
    }
    
    /**
     * Clears all god mode players.
     * Used when plugin is disabled.
     */
    public static void clearAllGodMode() {
        godModePlayers.clear();
    }
} 