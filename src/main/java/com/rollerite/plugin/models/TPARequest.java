package com.rollerite.plugin.models;

import org.bukkit.entity.Player;

public class TPARequest {
    
    private final Player from;
    private final Player to;
    private final long timestamp;
    
    public TPARequest(Player from, Player to, long timestamp) {
        this.from = from;
        this.to = to;
        this.timestamp = timestamp;
    }
    
    public Player getFrom() {
        return from;
    }
    
    public Player getTo() {
        return to;
    }
    
    public long getTimestamp() {
        return timestamp;
    }
} 