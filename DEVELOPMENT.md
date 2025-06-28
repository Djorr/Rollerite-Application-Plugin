# Development Guide

This document provides comprehensive guidance for developing and maintaining the UtilityPlugin.

## Table of Contents

1. [Project Overview](#project-overview)
2. [Development Environment](#development-environment)
3. [Architecture](#architecture)
4. [Coding Standards](#coding-standards)
5. [Testing](#testing)
6. [Building](#building)
7. [Contributing](#contributing)

## Project Overview

The UtilityPlugin is a comprehensive Minecraft utility plugin built with modern OOP principles. It provides essential server management and player utility commands with full tab completion support.

### Key Features
- Modern Java 17 architecture
- Service-oriented design
- Comprehensive testing
- Clean separation of concerns
- Professional error handling

## Development Environment

### Prerequisites
- **Java**: Version 17 or higher
- **Maven**: Version 3.6 or higher
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code
- **Minecraft Server**: Paper 1.21.5 for testing

### Setup
1. Clone the repository
2. Import as Maven project in your IDE
3. Install dependencies: `mvn dependency:resolve`
4. Run tests: `mvn test`

## Architecture

### Package Structure
```
com.utility.plugin/
├── commands/          # Command implementations
├── services/          # Business logic services
├── listeners/         # Event listeners
├── models/           # Data models
├── utils/            # Utility classes
├── gui/              # GUI components
└── UtilityPlugin.java # Main plugin class
```

### Design Patterns

#### Service Layer Pattern
Business logic is separated into service classes:
- `ConfigService`: Configuration management
- `MessageService`: Message formatting and sending
- `TPAService`: Teleport request management

#### Command Pattern
Each command extends `BaseCommand`:
```java
public class GamemodeCommand extends BaseCommand {
    public GamemodeCommand(UtilityPlugin plugin) {
        super(plugin, "utility.gamemode");
    }
    
    @Override
    protected boolean execute(CommandSender sender, String[] args) {
        // Command logic here
    }
}
```

#### Observer Pattern
Event-driven architecture with listeners:
- `GodModeListener`: Handles god mode events
- `TrashGUIListener`: Handles trash GUI interactions

### Key Classes

#### UtilityPlugin
Main plugin class with singleton pattern:
```java
public class UtilityPlugin extends JavaPlugin {
    private static UtilityPlugin instance;
    
    public static UtilityPlugin getInstance() {
        return instance;
    }
}
```

#### BaseCommand
Abstract base class for all commands:
- Implements `CommandExecutor` and `TabCompleter`
- Handles permission checking
- Provides common utility methods

#### Services
Service classes handle business logic:
- Dependency injection through constructor
- Clean separation of concerns
- Comprehensive error handling

## Coding Standards

### Naming Conventions
- **Classes**: PascalCase (e.g., `GamemodeCommand`)
- **Methods**: camelCase (e.g., `executeCommand()`)
- **Variables**: camelCase (e.g., `playerName`)
- **Constants**: UPPER_SNAKE_CASE (e.g., `DEFAULT_TIMEOUT`)
- **Packages**: lowercase (e.g., `com.utility.plugin.commands`)

### Documentation
- Comprehensive JavaDoc for all public methods
- Inline comments for complex logic
- README and CHANGELOG maintenance
- Code examples in documentation

### Error Handling
- Use appropriate exception types
- Provide meaningful error messages
- Log errors for debugging
- Graceful degradation when possible

### Logging
- Use appropriate log levels (INFO, WARNING, ERROR, DEBUG)
- Include context in log messages
- Avoid logging sensitive information
- Use structured logging when possible

## Testing

### Test Structure
Tests are organized in the `src/test/java` directory:
```
src/test/java/com/utility/plugin/
└── services/
    └── ConfigServiceTest.java
```

### Testing Framework
- **JUnit 5**: Test framework
- **Mockito**: Mocking framework
- **Maven Surefire**: Test execution

### Test Naming
- Test methods: `test[MethodName]_[Scenario]`
- Test classes: `[ClassName]Test`
- Clear, descriptive test names

### Example Test
```java
@Test
void testGetMessage_ValidPath() {
    // Arrange
    String expectedMessage = "Test message";
    when(config.getString("messages.test", "Message not found: test"))
        .thenReturn(expectedMessage);
    
    // Act
    String result = configService.getMessage("test");
    
    // Assert
    assertEquals(expectedMessage, result);
}
```

### Running Tests
```bash
mvn test                    # Run all tests
mvn test -Dtest=ConfigServiceTest  # Run specific test class
mvn test -Dtest=testGetMessage_ValidPath  # Run specific test method
```

## Building

### Maven Commands
```bash
mvn clean compile          # Clean and compile
mvn clean test             # Clean and run tests
mvn clean package          # Clean, test, and package
mvn clean install          # Clean, test, package, and install
```

### Build Artifacts
- **JAR File**: `target/utility-plugin-1.0.0.jar`
- **Source JAR**: `target/utility-plugin-1.0.0-sources.jar`
- **Test Reports**: `target/surefire-reports/`

### Build Configuration
The project uses Maven with the following plugins:
- **maven-compiler-plugin**: Java 17 compilation
- **maven-shade-plugin**: JAR packaging
- **maven-surefire-plugin**: Test execution

## Contributing

### Development Workflow
1. Create feature branch from `main`
2. Implement changes with tests
3. Run full test suite
4. Update documentation
5. Submit pull request

### Code Review Checklist
- [ ] Code follows style guidelines
- [ ] Tests are included and passing
- [ ] Documentation is updated
- [ ] No breaking changes (unless documented)
- [ ] Error handling is appropriate
- [ ] Logging is implemented

### Commit Messages
Use conventional commit format:
```
feat: add new gamemode command
fix: resolve TPA timeout issue
docs: update README with new features
test: add unit tests for ConfigService
```

### Pull Request Process
1. Fork the repository
2. Create feature branch
3. Make changes with tests
4. Update documentation
5. Submit pull request with description
6. Address review feedback
7. Merge after approval

## Configuration

### Development Configuration
For development, you can customize the configuration:
```yaml
# config.yml
prefix: "&8[&bUtilityPlugin&8] &r"
god:
  hunger: true
  fall-damage: true
tpa:
  timeout: 60
  cooldown: 30
```

### Testing Configuration
Tests use mocked configurations to ensure isolation and reliability.

## Troubleshooting

### Common Issues
1. **Compilation Errors**: Ensure Java 17+ is installed
2. **Test Failures**: Check mock configurations
3. **Plugin Loading**: Verify Paper API version compatibility
4. **Permission Issues**: Check permission node configuration

### Debug Information
Enable debug logging in your IDE or server:
- Set log level to FINE for detailed plugin logs
- Check console output for error messages
- Use debugger for step-by-step execution

## Resources

- [Paper API Documentation](https://papermc.io/javadocs/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito Documentation](https://javadoc.io/doc/org.mockito/mockito-core/latest/org/mockito/Mockito.html)

---

For additional support, please refer to the main README or create an issue on GitHub. 