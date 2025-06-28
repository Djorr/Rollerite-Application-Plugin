package com.utility.plugin.commands;

import com.utility.plugin.UtilityPlugin;
import com.utility.plugin.gui.TrashGUI;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Command handler for the /trash command.
 * Allows players to open a trash GUI to delete items.
 * 
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
public class TrashCommand extends BaseCommand {
    
    /**
     * Constructs a new TrashCommand instance.
     * 
     * @param plugin The main plugin instance
     */
    public TrashCommand(UtilityPlugin plugin) {
        super(plugin, "utility.trash");
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
        if (!commandUtils.isPlayer(sender)) {
            return true;
        }
        
        Player player = (Player) sender;
        TrashGUI trashGUI = new TrashGUI(plugin);
        trashGUI.open(player);
        
        plugin.getMessageService().sendMessage(sender, "trash-opened");
        
        return true;
    }
    
    /**
     * Provides tab completion for the trash command.
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