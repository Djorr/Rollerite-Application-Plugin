package com.utility.plugin.services;

import com.utility.plugin.UtilityPlugin;
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
 * @author UtilityPlugin Team
 * @version 1.0.0
 * @since 1.0.0
 */
@ExtendWith(MockitoExtension.class)
class ConfigServiceTest {
    
    @Mock
    private UtilityPlugin plugin;
    
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
        String expectedPrefix = "&8[&bUtilityPlugin&8] &r";
        when(config.getString("prefix", "&8[&bUtilityPlugin&8] &r")).thenReturn(expectedPrefix);
        
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
        when(config.getInt("tpa.timeout", 60)).thenReturn(expectedTimeout);
        
        // Act
        int result = configService.getTPATimeout();
        
        // Assert
        assertEquals(expectedTimeout, result);
        verify(config).getInt("tpa.timeout", 60);
    }
    
    /**
     * Test TPA cooldown retrieval.
     */
    @Test
    void testGetTPACooldown() {
        // Arrange
        int expectedCooldown = 30;
        when(config.getInt("tpa.cooldown", 30)).thenReturn(expectedCooldown);
        
        // Act
        int result = configService.getTPACooldown();
        
        // Assert
        assertEquals(expectedCooldown, result);
        verify(config).getInt("tpa.cooldown", 30);
    }
    
    /**
     * Test god hunger setting retrieval when enabled.
     */
    @Test
    void testIsGodHungerEnabled_True() {
        // Arrange
        when(config.getBoolean("god.hunger", true)).thenReturn(true);
        
        // Act
        boolean result = configService.isGodHungerEnabled();
        
        // Assert
        assertTrue(result);
        verify(config).getBoolean("god.hunger", true);
    }
    
    /**
     * Test god hunger setting retrieval when disabled.
     */
    @Test
    void testIsGodHungerEnabled_False() {
        // Arrange
        when(config.getBoolean("god.hunger", true)).thenReturn(false);
        
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
        when(config.getBoolean("god.fall-damage", true)).thenReturn(true);
        
        // Act
        boolean result = configService.isGodFallDamageEnabled();
        
        // Assert
        assertTrue(result);
        verify(config).getBoolean("god.fall-damage", true);
    }
    
    /**
     * Test message retrieval with odd number of replacements.
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
     * Test message retrieval with empty replacements.
     */
    @Test
    void testGetMessage_EmptyReplacements() {
        // Arrange
        String template = "Simple message";
        when(config.getString("messages.simple", "Message not found: simple")).thenReturn(template);
        
        // Act
        String result = configService.getMessage("simple");
        
        // Assert
        assertEquals(template, result);
    }
} 