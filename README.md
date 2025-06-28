# UtilityPlugin

A comprehensive Minecraft utility plugin built with modern OOP principles for Minecraft 1.21.5 using the Paper API.

## Features

### Commands

- **`/gamemode [gamemode] [player]`** - Change gamemode for yourself or another player
- **`/god [player]`** - Toggle god mode for yourself or another player
- **`/openinv <player>`** - Open another player's inventory
- **`/enderchest <player>`** - Open another player's ender chest
- **`/fix [player]`** - Repair all items for yourself or another player
- **`/tpa <player>`** - Send a teleport request to another player
- **`/tpaccept`** - Accept a teleport request
- **`/tpadeny`** - Deny a teleport request
- **`/trash`** - Open a trash GUI to delete items

### Key Features

- **Modern Architecture**: Built with clean OOP principles and service-oriented design
- **Comprehensive Permissions**: Granular permission system for all commands
- **Configurable Messages**: All messages are configurable via `config.yml`
- **Tab Completion**: Intelligent tab completion for all commands
- **God Mode**: Configurable god mode with hunger and fall damage prevention
- **TPA System**: Teleport request system with cooldowns and timeouts
- **Trash GUI**: Interactive GUI for item deletion
- **Unit Tests**: Comprehensive test coverage with JUnit and Mockito

## Installation

1. Download the latest JAR file from the releases page
2. Place the JAR file in your server's `plugins` folder
3. Restart your server
4. Configure the plugin via `plugins/UtilityPlugin/config.yml`

## Configuration

The plugin uses a comprehensive configuration system. See `config.yml` for all available options:

```yaml
# Message prefix for all plugin messages
prefix: "&8[&bUtilityPlugin&8] &r"

# God mode settings
god:
  hunger: true
  fall-damage: true

# TPA settings
tpa:
  timeout: 60
  cooldown: 30

# Messages configuration
messages:
  # All plugin messages are configurable here
```

## Permissions

- `utility.gamemode` - Allows changing gamemode
- `utility.god` - Allows toggling god mode
- `utility.openinv` - Allows opening other players' inventories
- `utility.enderchest` - Allows opening other players' ender chests
- `utility.fix` - Allows repairing items
- `utility.tpa` - Allows using teleport request commands
- `utility.trash` - Allows using the trash GUI
- `utility.*` - Gives access to all utility commands

## Development

### Building from Source

1. Clone the repository
2. Ensure you have Java 17+ and Maven installed
3. Run `mvn clean package`
4. The compiled JAR will be in the `target` directory

### Project Structure

```
src/
├── main/
│   ├── java/com/utility/plugin/
│   │   ├── commands/          # Command implementations
│   │   ├── services/          # Service layer
│   │   ├── listeners/         # Event listeners
│   │   ├── models/           # Data models
│   │   ├── utils/            # Utility classes
│   │   ├── gui/              # GUI components
│   │   └── UtilityPlugin.java # Main plugin class
│   └── resources/
│       ├── plugin.yml        # Plugin metadata
│       └── config.yml        # Default configuration
└── test/
    └── java/com/utility/plugin/
        └── services/         # Unit tests
```

### Running Tests

```bash
mvn test
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For support, please open an issue on GitHub or contact the development team.

## Changelog

See [CHANGELOG.md](CHANGELOG.md) for a detailed history of changes.

## Acknowledgments

- Built for Minecraft 1.21.5 using Paper API
- Uses modern Java 17 features
- Comprehensive test coverage with JUnit 5 and Mockito

