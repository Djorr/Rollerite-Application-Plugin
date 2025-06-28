# Development Guide

This document provides comprehensive guidance for developing and maintaining the Rollerite Plugin.

## Architecture Overview

The plugin follows a layered architecture pattern with clear separation of concerns:

### Layer Structure

```
┌─────────────────────────────────────┐
│           Commands Layer            │  ← User interface
├─────────────────────────────────────┤
│           Services Layer            │  ← Business logic
├─────────────────────────────────────┤
│           Models Layer              │  ← Data structures
├─────────────────────────────────────┤
│           Utils Layer               │  ← Shared utilities
└─────────────────────────────────────┘
```

### Package Organization

- **`commands/`**: Command implementations extending `BaseCommand`
- **`services/`**: Business logic services (ConfigService, MessageService, TPAService)
- **`listeners/`**: Event listeners for Bukkit events
- **`models/`**: Data transfer objects and domain models
- **`utils/`**: Utility classes and helper methods
- **`gui/`**: GUI components and inventory management

## Design Patterns

### 1. Service Layer Pattern
Business logic is encapsulated in service classes:
- `ConfigService`: Configuration management
- `MessageService`: Message formatting and delivery
- `TPAService`: Teleport request management

### 2. Command Pattern
Each command is implemented as a separate class extending `BaseCommand`:
```java
public class GamemodeCommand extends BaseCommand {
    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        // Command implementation
    }
}
```

### 3. Observer Pattern
Event-driven architecture using Bukkit's event system:
```java
@EventHandler(priority = EventPriority.HIGH)
public void onEntityDamage(EntityDamageEvent event) {
    // Event handling logic
}
```

### 4. Singleton Pattern
Plugin instance management for global access:
```java
public static RolleritePlugin getInstance() {
    return instance;
}
```

## Coding Standards

### JavaDoc Documentation
All public methods and classes must have comprehensive JavaDoc:

```java
/**
 * Executes the gamemode command logic.
 * 
 * @param sender The command sender (player or console)
 * @param args Command arguments
 * @return true if command executed successfully, false otherwise
 * @throws IllegalArgumentException if game mode is invalid
 */
```

### Naming Conventions
- **Classes**: PascalCase (e.g., `GamemodeCommand`)
- **Methods**: camelCase (e.g., `executeCommand`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_TIMEOUT`)
- **Packages**: lowercase (e.g., `com.rollerite.plugin.commands`)

### Error Handling
- Use meaningful error messages
- Log errors with appropriate levels
- Return boolean values for success/failure
- Handle null checks explicitly

### Logging
Use appropriate log levels:
```java
getLogger().info("Plugin enabled successfully");
getLogger().warning("Configuration issue detected");
getLogger().severe("Critical error occurred");
getLogger().fine("Debug information");
```

## Testing Strategy

### Unit Testing
- Test all service methods
- Mock dependencies using Mockito
- Use descriptive test method names
- Follow AAA pattern (Arrange, Act, Assert)

### Test Structure
```java
@Test
void testMethodName_Scenario_ExpectedResult() {
    // Arrange
    // Set up test data and mocks
    
    // Act
    // Execute the method under test
    
    // Assert
    // Verify the results
}
```

### Integration Testing
- Test command execution with mocked players
- Verify event handling
- Test configuration loading

## Configuration Management

### Message System
All user-facing messages are externalized to `config.yml`:
```yaml
messages:
  prefix: "&8[&bRollerite&8] &r"
  no-permission: "&cYou don't have permission to use this command!"
```

### Settings
Plugin behavior is configurable:
```yaml
settings:
  tpa-timeout: 60
  tpa-cooldown: 10
  god-hunger: false
```

## Permission System

### Permission Nodes
Each command has its own permission node:
- `rollerite.gamemode`
- `rollerite.god`
- `rollerite.openinv`
- etc.

### Permission Hierarchy
- Admin commands: `op` by default
- User commands: `true` by default
- Configurable via `plugin.yml`

## Event Handling

### Event Priorities
Use appropriate event priorities:
- `LOWEST`: First to execute
- `LOW`: Early execution
- `NORMAL`: Default priority
- `HIGH`: Late execution
- `HIGHEST`: Last to execute
- `MONITOR`: Read-only, cannot cancel

### Event Cancellation
Only cancel events when necessary:
```java
@EventHandler(priority = EventPriority.HIGH)
public void onEntityDamage(EntityDamageEvent event) {
    if (shouldPreventDamage(event)) {
        event.setCancelled(true);
    }
}
```

## Performance Considerations

### Memory Management
- Use weak references for player tracking
- Clear collections on plugin disable
- Avoid memory leaks in event listeners

### Efficiency
- Cache frequently accessed data
- Use appropriate data structures
- Minimize object creation in loops

### Async Operations
- Use Bukkit's scheduler for delayed tasks
- Avoid blocking operations on main thread
- Handle async operations safely

## Security Best Practices

### Input Validation
- Validate all user input
- Sanitize player names
- Check permissions before operations

### Data Protection
- Don't expose sensitive information
- Validate configuration values
- Handle exceptions gracefully

## Debugging

### Logging Levels
- `FINE`: Detailed debug information
- `INFO`: General information
- `WARNING`: Potential issues
- `SEVERE`: Critical errors

### Debug Commands
Consider adding debug commands for development:
```java
if (plugin.isDebugMode()) {
    getLogger().fine("Debug: " + debugInfo);
}
```

## Deployment

### Building
```bash
mvn clean package
```

### Testing
```bash
mvn test
```

### Installation
1. Copy JAR to `plugins/` folder
2. Restart server
3. Verify plugin loads without errors
4. Check configuration generation

## Maintenance

### Version Control
- Use semantic versioning
- Create feature branches
- Write meaningful commit messages
- Tag releases

### Documentation
- Keep README updated
- Document configuration changes
- Update API documentation
- Maintain changelog

### Code Review
- Review all changes
- Check for security issues
- Verify performance impact
- Ensure test coverage

## Troubleshooting

### Common Issues
1. **Plugin not loading**: Check dependencies and Java version
2. **Commands not working**: Verify permissions and registration
3. **Configuration errors**: Check YAML syntax
4. **Performance issues**: Review event handling and loops

### Debug Steps
1. Check server logs for errors
2. Verify plugin.yml syntax
3. Test with minimal configuration
4. Check for conflicting plugins

## Contributing

### Development Workflow
1. Fork the repository
2. Create feature branch
3. Implement changes with tests
4. Update documentation
5. Submit pull request

### Code Quality
- Follow coding standards
- Add comprehensive tests
- Update documentation
- Ensure backward compatibility

This development guide ensures consistent, maintainable, and professional code quality across the project. 