package com.utility.plugin.services;

import com.utility.plugin.UtilityPlugin;
import com.utility.plugin.models.TPARequest;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Service class for handling TPA (Teleport Request) functionality.
 * 
 * @author UtilityPlugin Team
 */
public class TPAService {
    
    private final UtilityPlugin plugin;
    private final Map<UUID, TPARequest> pendingRequests = new HashMap<>();
    private final Map<UUID, Long> cooldowns = new HashMap<>();
    
    public TPAService(UtilityPlugin plugin) {
        this.plugin = plugin;
    }
    
    public boolean sendTPARequest(Player from, Player to) {
        UUID fromId = from.getUniqueId();
        UUID toId = to.getUniqueId();
        
        // Check cooldown
        if (isOnCooldown(fromId)) {
            long remaining = getCooldownRemaining(fromId);
            plugin.getMessageService().sendMessage(from, "tpa-cooldown", "cooldown", String.valueOf(remaining));
            return false;
        }
        
        // Remove any existing request from this player
        pendingRequests.remove(fromId);
        
        // Create new request
        TPARequest request = new TPARequest(from, to, System.currentTimeMillis());
        pendingRequests.put(fromId, request);
        
        // Set cooldown
        setCooldown(fromId);
        
        // Schedule timeout
        scheduleTimeout(fromId);
        
        return true;
    }
    
    public boolean acceptTPARequest(Player player) {
        TPARequest request = getRequestToPlayer(player);
        if (request == null) {
            plugin.getMessageService().sendMessage(player, "tpa-no-request");
            return false;
        }
        
        Player from = request.getFrom();
        if (!from.isOnline()) {
            plugin.getMessageService().sendMessage(player, "tpa-timeout");
            pendingRequests.remove(request.getFrom().getUniqueId());
            return false;
        }
        
        // Teleport
        from.teleport(player.getLocation());
        
        // Send messages
        plugin.getMessageService().sendMessage(player, "tpa-accepted");
        plugin.getMessageService().sendMessage(from, "tpa-accepted-other", "player", player.getName());
        
        // Remove request
        pendingRequests.remove(request.getFrom().getUniqueId());
        
        return true;
    }
    
    public boolean denyTPARequest(Player player) {
        TPARequest request = getRequestToPlayer(player);
        if (request == null) {
            plugin.getMessageService().sendMessage(player, "tpa-no-request");
            return false;
        }
        
        Player from = request.getFrom();
        if (from.isOnline()) {
            plugin.getMessageService().sendMessage(from, "tpa-denied-other", "player", player.getName());
        }
        
        plugin.getMessageService().sendMessage(player, "tpa-denied");
        pendingRequests.remove(request.getFrom().getUniqueId());
        
        return true;
    }
    
    private TPARequest getRequestToPlayer(Player player) {
        for (TPARequest request : pendingRequests.values()) {
            if (request.getTo().equals(player)) {
                return request;
            }
        }
        return null;
    }
    
    private boolean isOnCooldown(UUID playerId) {
        if (!cooldowns.containsKey(playerId)) {
            return false;
        }
        return System.currentTimeMillis() - cooldowns.get(playerId) < plugin.getConfigService().getTPACooldown() * 1000L;
    }
    
    private long getCooldownRemaining(UUID playerId) {
        if (!cooldowns.containsKey(playerId)) {
            return 0;
        }
        long elapsed = System.currentTimeMillis() - cooldowns.get(playerId);
        return Math.max(0, (plugin.getConfigService().getTPACooldown() * 1000L) - elapsed) / 1000L;
    }
    
    private void setCooldown(UUID playerId) {
        cooldowns.put(playerId, System.currentTimeMillis());
    }
    
    private void scheduleTimeout(UUID playerId) {
        new BukkitRunnable() {
            @Override
            public void run() {
                TPARequest request = pendingRequests.remove(playerId);
                if (request != null && request.getFrom().isOnline()) {
                    plugin.getMessageService().sendMessage(request.getFrom(), "tpa-timeout");
                    if (request.getTo().isOnline()) {
                        plugin.getMessageService().sendMessage(request.getTo(), "tpa-timeout-other", "player", request.getFrom().getName());
                    }
                }
            }
        }.runTaskLater(plugin, plugin.getConfigService().getTPATimeout() * 20L);
    }
    
    public void shutdown() {
        pendingRequests.clear();
        cooldowns.clear();
    }
} 