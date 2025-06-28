# Changelog

All notable changes to the UtilityPlugin will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-01-01

### Added
- Initial release of UtilityPlugin
- **Gamemode Command** (`/gamemode`)
  - Change gamemode for yourself or other players
  - Support for all gamemodes (survival, creative, adventure, spectator)
  - Tab completion for gamemodes and player names
  - Permission: `utility.gamemode`

- **God Command** (`/god`)
  - Toggle god mode for yourself or other players
  - Configurable hunger and fall damage prevention
  - Automatic cleanup when players leave
  - Permission: `utility.god`

- **OpenInv Command** (`/openinv`)
  - Open another player's inventory
  - Real-time inventory updates
  - Tab completion for player names
  - Permission: `utility.openinv`

- **EnderChest Command** (`/enderchest`)
  - Open another player's ender chest
  - Secure storage access
  - Tab completion for player names
  - Permission: `utility.enderchest`

- **Fix Command** (`/fix`)
  - Repair all items for yourself or other players
  - Repairs main inventory, armor, and offhand items
  - Tab completion for player names
  - Permission: `utility.fix`

- **TPA System** (`/tpa`, `/tpaccept`, `/tpadeny`)
  - Send teleport requests to other players
  - Accept or deny incoming requests
  - Configurable timeouts and cooldowns
  - Self-targeting support
  - Permission: `utility.tpa`

- **Trash Command** (`/trash`)
  - Interactive GUI for item deletion
  - 6-row chest interface with decorative border
  - Item counting and deletion notifications
  - Permission: `utility.trash`

### Technical Features
- **Modern Architecture**
  - Service-oriented design
  - Clean separation of concerns
  - Comprehensive error handling
  - Professional logging

- **Configuration System**
  - Fully configurable messages
  - Customizable settings
  - Default configuration generation
  - Hot-reload support

- **Permission System**
  - Granular permissions for each command
  - Wildcard permission support
  - Default permission levels
  - Permission inheritance

- **Tab Completion**
  - Intelligent completion for all commands
  - Permission-aware suggestions
  - Real-time player list updates
  - Smart filtering

- **Testing**
  - Comprehensive unit tests
  - JUnit 5 and Mockito integration
  - Test coverage for core services
  - Automated test execution

### Configuration
- `config.yml` with all plugin settings
- Customizable message prefix
- God mode configuration options
- TPA timeout and cooldown settings
- All messages configurable

### Dependencies
- Minecraft 1.21.5 (Paper API)
- Java 17+
- Maven for building
- JUnit 5 for testing
- Mockito for mocking

## [Unreleased]

### Planned Features
- Additional utility commands
- Enhanced GUI systems
- Performance optimizations
- Extended configuration options

### Known Issues
- None at this time

---

## Contributors

- UtilityPlugin Team - Initial development and architecture

## [Unreleased]

### Planned
- Additional utility commands
- Enhanced GUI system
- Performance optimizations
- Extended configuration options

## [1.0.0] - 2024-01-XX

### Added
- **Core Plugin Framework**
  - Main plugin class with proper lifecycle management
  - Service layer architecture for clean separation of concerns
  - Comprehensive configuration system
  - Message service for internationalization support

- **Command System**
  - `/gamemode <type> [player]` - Change game modes with optional player targeting
  - `/god [player]` - Toggle god mode (invincibility) for players
  - `/openinv <player>` - Open and edit other players' inventories
  - `/enderchest [player]` - Access ender chests with optional player targeting
  - `/fix [player]` - Repair items in main hand
  - `/tpa <player>` - Send teleport requests to players
  - `/tpaccept` - Accept incoming teleport requests
  - `/tpadeny` - Deny incoming teleport requests
  - `/trash` - Open trash GUI for item disposal

- **Permission System**
  - Individual permission nodes for each command
  - Configurable default permissions in plugin.yml
  - Proper permission checking with user-friendly messages

- **Configuration Management**
  - Externalized message system in config.yml
  - Configurable TPA timeout and cooldown settings
  - God mode behavior configuration options
  - Prefix customization support

- **Event Handling**
  - God mode damage prevention system
  - Optional hunger prevention for god mode
  - Trash GUI item cleanup on close
  - Player quit cleanup for god mode

- **GUI System**
  - Trash GUI with decorative border
  - Item disposal functionality
  - Inventory interaction handling

- **Utility Classes**
  - CommandUtils for common command validation
  - BaseCommand abstract class for consistent command structure
  - TPARequest model for teleport request management

### Technical Features
- **Modern Java Development**
  - Java 17 compatibility
  - Maven build system with proper dependency management
  - Comprehensive JavaDoc documentation
  - Professional code structure and naming conventions

- **Testing Framework**
  - JUnit 5 integration
  - Mockito for dependency mocking
  - Unit tests for core services
  - Test coverage for configuration management

- **Build System**
  - Maven Shade plugin for JAR packaging
  - Surefire plugin for test execution
  - Build scripts for Windows and Unix systems
  - Proper artifact naming and versioning

- **Documentation**
  - Comprehensive README with installation and usage instructions
  - Development guide with architecture overview
  - API documentation with JavaDoc
  - Configuration examples and troubleshooting

### Security
- Input validation for all user commands
- Permission-based access control
- Safe player targeting with null checks
- Console command safety measures

### Performance
- Efficient data structures for player tracking
- Optimized event handling with appropriate priorities
- Memory leak prevention in listeners
- Cleanup procedures on plugin disable

### Compatibility
- **Minecraft 1.21.5** support (latest version)
- Paper/Spigot API compatibility
- Backward compatibility considerations
- Proper API version specification
- Dependency management for Bukkit APIs

## [0.1.0] - 2024-01-XX

### Added
- Initial project structure
- Basic Maven configuration
- Core plugin framework setup
- Development environment preparation

---

## Version History Summary

### Major Versions
- **1.x.x**: Production-ready releases with full feature set
- **0.x.x**: Development and beta releases

### Version Numbering
- **Major**: Breaking changes or major feature additions
- **Minor**: New features with backward compatibility
- **Patch**: Bug fixes and minor improvements

### Release Types
- **Stable**: Production-ready releases
- **Beta**: Feature-complete but may have minor issues
- **Alpha**: Early development releases

---

## Migration Guide

### From 0.x.x to 1.0.0
- No breaking changes - this is the initial stable release
- All features are new additions
- Configuration files will be generated automatically

### Future Migrations
- Check this changelog for breaking changes
- Review configuration changes between versions
- Test thoroughly before production deployment

---

## Support

For issues and questions:
1. Check the documentation in README.md
2. Review the development guide in DEVELOPMENT.md
3. Search existing issues on GitHub
4. Create a new issue with detailed information

---

*This changelog is maintained according to the [Keep a Changelog](https://keepachangelog.com/) standard.* 