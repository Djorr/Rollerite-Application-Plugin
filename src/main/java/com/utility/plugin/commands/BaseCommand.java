package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import com.utility.plugin.utils.CommandUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract base class for all plugin commands.
 * Provides common functionality for command execution and tab completion.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class BaseCommand implements CommandExecutor, TabCompleter {
    
    protected final UtilityPlugin plugin;
    protected final CommandUtils commandUtils;
    protected final String permission;
    
    /**
     * Constructs a new BaseCommand instance.
     * 
     * @param plugin The main plugin instance
     * @param permission The permission required to use this command
     */
    public BaseCommand(UtilityPlugin plugin, String permission) {
        this.plugin = plugin;
        this.commandUtils = new CommandUtils(plugin);
        this.permission = permission;
    }
    
    /**
     * Executes the command logic.
     * 
     * @param sender The command sender
     * @param command The command that was executed
     * @param label The alias used
     * @param args The command arguments
     * @return true if the command was handled successfully
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!commandUtils.hasPermission(sender, permission)) {
            return true;
        }
        
        return execute(sender, args);
    }
    
    /**
     * Provides tab completion for the command.
     * 
     * @param sender The command sender
     * @param command The command
     * @param alias The alias used
     * @param args The command arguments
     * @return List of tab completion options
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (!sender.hasPermission(permission)) {
            return new ArrayList<>();
        }
        
        return tabComplete(sender, args);
    }
    
    /**
     * Executes the command logic. Must be implemented by subclasses.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return true if the command was handled successfully
     */
    protected abstract boolean execute(CommandSender sender, String[] args);
    
    /**
     * Provides tab completion for the command. Can be overridden by subclasses.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return List of tab completion options
     */
    protected List<String> tabComplete(CommandSender sender, String[] args) {
        // Default implementation returns empty list
        return new ArrayList<>();
    }
    
    /**
     * Gets a list of online player names for tab completion.
     * 
     * @param sender The command sender
     * @param partialName The partial name to filter by
     * @return List of matching player names
     */
    protected List<String> getOnlinePlayerNames(CommandSender sender, String partialName) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .filter(name -> name.toLowerCase().startsWith(partialName.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets a list of online player names for tab completion.
     * 
     * @param sender The command sender
     * @return List of all online player names
     */
    protected List<String> getOnlinePlayerNames(CommandSender sender) {
        return Bukkit.getOnlinePlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
} 