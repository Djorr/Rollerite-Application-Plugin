package com.rollerite.plugin.services;

import com.rollerite.plugin.RolleritePlugin;
import org.bukkit.configuration.file.FileConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the ConfigService class.
 * Tests configuration loading, message retrieval, and settings access.
 * 
 * @author Rollerite Plugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {
    
    @Mock
    private RolleritePlugin plugin;
    
    @Mock
    private FileConfiguration config;
    
    private ConfigService configService;
    
    /**
     * Set up test fixtures before each test method.
     */
    @BeforeEach
    void setUp() {
        // Mock plugin behavior
        when(plugin.getConfig()).thenReturn(config);
        
        // Create ConfigService instance
        configService = new ConfigService(plugin);
    }
    
    /**
     * Test that the service loads configuration correctly.
     */
    @Test
    void testLoadConfig() {
        // Verify that saveDefaultConfig and reloadConfig are called
        verify(plugin).saveDefaultConfig();
        verify(plugin).reloadConfig();
    }
    
    /**
     * Test message retrieval with valid path.
     */
    @Test
    void testGetMessage_ValidPath() {
        // Arrange
        String expectedMessage = "Test message";
        when(config.getString("messages.test", "Message not found: test")).thenReturn(expectedMessage);
        
        // Act
        String result = configService.getMessage("test");
        
        // Assert
        assertEquals(expectedMessage, result);
        verify(config).getString("messages.test", "Message not found: test");
    }
    
    /**
     * Test message retrieval with invalid path returns default.
     */
    @Test
    void testGetMessage_InvalidPath() {
        // Arrange
        String expectedDefault = "Message not found: invalid";
        when(config.getString("messages.invalid", expectedDefault)).thenReturn(expectedDefault);
        
        // Act
        String result = configService.getMessage("invalid");
        
        // Assert
        assertEquals(expectedDefault, result);
    }
    
    /**
     * Test message retrieval with replacements.
     */
    @Test
    void testGetMessage_WithReplacements() {
        // Arrange
        String template = "Hello {name}, you are {age} years old!";
        String expected = "Hello John, you are 25 years old!";
        when(config.getString("messages.greeting", "Message not found: greeting")).thenReturn(template);
        
        // Act
        String result = configService.getMessage("greeting", "name", "John", "age", "25");
        
        // Assert
        assertEquals(expected, result);
    }
    
    /**
     * Test prefix retrieval.
     */
    @Test
    void testGetPrefix() {
        // Arrange
        String expectedPrefix = "&8[&bRollerite&8] &r";
        when(config.getString("messages.prefix", "Message not found: prefix")).thenReturn(expectedPrefix);
        
        // Act
        String result = configService.getPrefix();
        
        // Assert
        assertEquals(expectedPrefix, result);
    }
    
    /**
     * Test TPA timeout retrieval.
     */
    @Test
    void testGetTPATimeout() {
        // Arrange
        int expectedTimeout = 60;
        when(config.getInt("settings.tpa-timeout", 60)).thenReturn(expectedTimeout);
        
        // Act
        int result = configService.getTPATimeout();
        
        // Assert
        assertEquals(expectedTimeout, result);
        verify(config).getInt("settings.tpa-timeout", 60);
    }
    
    /**
     * Test TPA cooldown retrieval.
     */
    @Test
    void testGetTPACooldown() {
        // Arrange
        int expectedCooldown = 10;
        when(config.getInt("settings.tpa-cooldown", 10)).thenReturn(expectedCooldown);
        
        // Act
        int result = configService.getTPACooldown();
        
        // Assert
        assertEquals(expectedCooldown, result);
        verify(config).getInt("settings.tpa-cooldown", 10);
    }
    
    /**
     * Test god hunger setting retrieval when enabled.
     */
    @Test
    void testIsGodHungerEnabled_True() {
        // Arrange
        when(config.getBoolean("settings.god-hunger", false)).thenReturn(true);
        
        // Act
        boolean result = configService.isGodHungerEnabled();
        
        // Assert
        assertTrue(result);
        verify(config).getBoolean("settings.god-hunger", false);
    }
    
    /**
     * Test god hunger setting retrieval when disabled.
     */
    @Test
    void testIsGodHungerEnabled_False() {
        // Arrange
        when(config.getBoolean("settings.god-hunger", false)).thenReturn(false);
        
        // Act
        boolean result = configService.isGodHungerEnabled();
        
        // Assert
        assertFalse(result);
    }
    
    /**
     * Test god fall damage setting retrieval.
     */
    @Test
    void testIsGodFallDamageEnabled() {
        // Arrange
        when(config.getBoolean("settings.god-fall-damage", false)).thenReturn(true);
        
        // Act
        boolean result = configService.isGodFallDamageEnabled();
        
        // Assert
        assertTrue(result);
        verify(config).getBoolean("settings.god-fall-damage", false);
    }
    
    /**
     * Test message replacement with odd number of parameters.
     */
    @Test
    void testGetMessage_OddReplacements() {
        // Arrange
        String template = "Hello {name}!";
        when(config.getString("messages.odd", "Message not found: odd")).thenReturn(template);
        
        // Act
        String result = configService.getMessage("odd", "name", "John", "extra");
        
        // Assert
        assertEquals("Hello John!", result);
    }
    
    /**
     * Test message replacement with empty parameters.
     */
    @Test
    void testGetMessage_EmptyReplacements() {
        // Arrange
        String template = "Hello {name}!";
        when(config.getString("messages.empty", "Message not found: empty")).thenReturn(template);
        
        // Act
        String result = configService.getMessage("empty");
        
        // Assert
        assertEquals(template, result);
    }
} 