package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Command handler for the /gamemode command.
 * Allows players to change game modes for themselves or other players.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class GamemodeCommand extends BaseCommand {
    
    private static final List<String> GAMEMODE_NAMES = Arrays.asList(
        "survival", "creative", "adventure", "spectator",
        "s", "c", "a", "sp",
        "0", "1", "2", "3"
    );
    
    /**
     * Constructs a new GamemodeCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public GamemodeCommand(UtilityPlugin plugin) {
        super(plugin, "utility.gamemode");
    }
    
    /**
     * Executes the gamemode command logic.
     * 
     * @param sender The command sender (player or console)
     * @param args Command arguments
     * @return true if command executed successfully, false otherwise
     */
    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        if (args.length < 1) {
            return false;
        }
        
        GameMode gameMode = parseGameMode(args[0]);
        if (gameMode == null) {
            plugin.getMessageService().sendMessage(sender, "invalid-gamemode");
            return true;
        }
        
        Player target = null;
        if (args.length > 1) {
            target = commandUtils.getTargetPlayer(sender, args[1]);
            if (target == null) {
                return true;
            }
        } else {
            if (!commandUtils.isPlayer(sender)) {
                return true;
            }
            target = (Player) sender;
        }
        
        target.setGameMode(gameMode);
        
        if (target.equals(sender)) {
            plugin.getMessageService().sendMessage(sender, "gamemode-changed", 
                "gamemode", gameMode.name().toLowerCase());
        } else {
            plugin.getMessageService().sendMessage(sender, "gamemode-changed-other", 
                "gamemode", gameMode.name().toLowerCase(), 
                "player", target.getName());
        }
        
        return true;
    }
    
    /**
     * Provides tab completion for the gamemode command.
     * 
     * @param sender The command sender
     * @param args The command arguments
     * @return List of tab completion options
     */
    @Override
    protected List<String> tabComplete(CommandSender sender, String[] args) {
        List<String> completions = new ArrayList<>();
        
        if (args.length == 1) {
            // Tab complete game mode names
            String partial = args[0].toLowerCase();
            for (String gamemode : GAMEMODE_NAMES) {
                if (gamemode.startsWith(partial)) {
                    completions.add(gamemode);
                }
            }
        } else if (args.length == 2) {
            // Tab complete player names
            completions = getOnlinePlayerNames(sender, args[1]);
        }
        
        return completions;
    }
    
    /**
     * Parses a game mode string into a GameMode enum.
     * 
     * @param input The input string to parse
     * @return The GameMode, or null if invalid
     */
    private GameMode parseGameMode(String input) {
        switch (input.toLowerCase()) {
            case "survival":
            case "s":
            case "0":
                return GameMode.SURVIVAL;
            case "creative":
            case "c":
            case "1":
                return GameMode.CREATIVE;
            case "adventure":
            case "a":
            case "2":
                return GameMode.ADVENTURE;
            case "spectator":
            case "sp":
            case "3":
                return GameMode.SPECTATOR;
            default:
                return null;
        }
    }
} 