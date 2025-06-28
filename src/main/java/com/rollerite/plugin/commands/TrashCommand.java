package com.rollerite.plugin.commands;

import com.rollerite.plugin.RolleritePlugin;
import com.rollerite.plugin.gui.TrashGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /trash command.
 * Allows players to open a GUI where they can drop items that will be deleted when closed.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrashCommand extends BaseCommand {
    
    /**
     * Constructs a new TrashCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TrashCommand(RolleritePlugin plugin) {
        super(plugin, "rollerite.trash");
    }
    
    /**
     * Executes the trash command logic.
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
        
        // Open the trash GUI for the player
        Player player = (Player) sender;
        TrashGUI trashGUI = new TrashGUI(plugin);
        trashGUI.open(player);
        
        // Send success message
        plugin.getMessageService().sendMessage(sender, "trash-opened");
        
        return true;
    }
    
    /**
     * Provides tab completion for the trash command.
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