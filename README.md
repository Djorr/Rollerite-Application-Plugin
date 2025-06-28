# Rollerite Plugin

A comprehensive Minecraft utility plugin built with modern Java practices and clean architecture. This plugin provides essential server management and player utility commands with full tab completion support.

## 🚀 Features

### Commands

| Command | Description | Permission | Usage |
|---------|-------------|------------|-------|
| `/gamemode <type> [player]` | Change game mode | `rollerite.gamemode` | `/gamemode creative` or `/gamemode survival PlayerName` |
| `/god [player]` | Toggle god mode (invincibility) | `rollerite.god` | `/god` or `/god PlayerName` |
| `/openinv <player>` | Open another player's inventory | `rollerite.openinv` | `/openinv PlayerName` |
| `/enderchest [player]` | Open ender chest | `rollerite.enderchest` | `/enderchest` or `/enderchest PlayerName` |
| `/fix [player]` | Repair item in main hand | `rollerite.fix` | `/fix` or `/fix PlayerName` |
| `/tpa <player>` | Send teleport request | `rollerite.tpa` | `/tpa PlayerName` |
| `/tpaccept` | Accept teleport request | `rollerite.tpa` | `/tpaccept` |
| `/tpadeny` | Deny teleport request | `rollerite.tpa` | `/tpadeny` |
| `/trash` | Open trash GUI | `rollerite.trash` | `/trash` |

### Key Features

- **Modern Architecture**: Built using clean separation of concerns with services, commands, and listeners
- **Comprehensive Permissions**: Each command has its own permission node
- **Configurable Messages**: All messages are customizable via `config.yml`
- **Player Targeting**: Most commands support optional player targeting
- **Console Support**: Commands work from console when player is specified
- **God Mode System**: Complete invincibility with optional hunger prevention
- **TPA System**: Teleport requests with timeouts and cooldowns
- **Trash GUI**: Visual interface for item disposal
- **Tab Completion**: Full tab completion support for all commands
- **Self-Targeting**: Commands work on yourself when using your own name

## 📋 Detailed Command Guide

### Game Mode Management (`/gamemode`)

**Description**: Change the game mode of yourself or another player.

**Usage Examples**:
```bash
/gamemode creative          # Set your own game mode to creative
/gamemode survival Player1  # Set Player1's game mode to survival
/gamemode adventure Player2 # Set Player2's game mode to adventure
/gamemode spectator Player3 # Set Player3's game mode to spectator
```

**Tab Completion**:
- First argument: `survival`, `creative`, `adventure`, `spectator`, `s`, `c`, `a`, `sp`, `0`, `1`, `2`, `3`
- Second argument: Online player names

**Features**:
- Supports both full names and abbreviations
- Works with numeric values (0-3)
- Console can target specific players
- Permission: `rollerite.gamemode` (default: op)

### God Mode (`/god`)

**Description**: Toggle invincibility for yourself or another player.

**Usage Examples**:
```bash
/god          # Toggle god mode for yourself
/god Player1  # Toggle god mode for Player1
```

**Tab Completion**:
- First argument: Online player names

**Features**:
- Complete damage immunity
- Optional hunger prevention (configurable)
- Automatic cleanup when player leaves
- Permission: `rollerite.god` (default: op)

### Inventory Management (`/openinv`)

**Description**: Open and edit another player's inventory (or your own).

**Usage Examples**:
```bash
/openinv Player1     # Open Player1's inventory
/openinv YourName    # Open your own inventory
```

**Tab Completion**:
- First argument: Online player names

**Features**:
- Full inventory access and editing
- Works on your own inventory
- Real-time inventory updates
- Permission: `rollerite.openinv` (default: op)

### Ender Chest Access (`/enderchest`)

**Description**: Open your own or another player's ender chest.

**Usage Examples**:
```bash
/enderchest          # Open your own ender chest
/enderchest Player1  # Open Player1's ender chest
```

**Tab Completion**:
- First argument: Online player names

**Features**:
- Access to ender chest storage
- Works on your own ender chest
- Secure storage access
- Permission: `rollerite.enderchest` (default: op)

### Item Repair (`/fix`)

**Description**: Fully repair the item held in your main hand.

**Usage Examples**:
```bash
/fix          # Repair item in your main hand
/fix Player1  # Repair item in Player1's main hand
```

**Tab Completion**:
- First argument: Online player names

**Features**:
- Repairs any damageable item to full durability
- Works on tools, weapons, and armor
- Checks if item is already repaired
- Permission: `rollerite.fix` (default: op)

### Teleport Request System (`/tpa`, `/tpaccept`, `/tpadeny`)

**Description**: Send, accept, or deny teleport requests between players.

**Usage Examples**:
```bash
/tpa Player1        # Send teleport request to Player1
/tpa YourName       # Send teleport request to yourself
/tpaccept           # Accept incoming teleport request
/tpadeny            # Deny incoming teleport request
```

**Tab Completion**:
- `/tpa` first argument: Online player names
- `/tpaccept` and `/tpadeny`: No arguments needed

**Features**:
- **Self-TPA Support**: Can send requests to yourself
- **Timeout System**: Requests expire after 60 seconds (configurable)
- **Cooldown System**: 10-second cooldown between requests (configurable)
- **Real Requests**: All requests can be accepted or denied
- **Automatic Cleanup**: Expired requests are automatically removed
- Permission: `rollerite.tpa` (default: true)

### Trash System (`/trash`)

**Description**: Open a GUI where you can drop items that will be permanently deleted when closed.

**Usage Examples**:
```bash
/trash              # Open trash GUI
```

**Features**:
- **Visual Interface**: 6-row chest-like GUI with decorative border
- **Item Disposal**: All items are permanently deleted when GUI closes
- **Item Counting**: Shows how many items were deleted
- **Border Protection**: Cannot remove decorative border items
- **Real-time Feedback**: Immediate item deletion notification
- Permission: `rollerite.trash` (default: true)

## 🎯 Tab Completion

All commands feature intelligent tab completion:

- **Game Modes**: `/gamemode` shows available game modes (survival, creative, adventure, spectator)
- **Player Names**: Commands with player parameters show online player names
- **Smart Filtering**: Tab completion filters based on partial input
- **Permission Aware**: Only shows options the player has permission to use
- **Real-time Updates**: Player list updates as players join/leave

## ⚙️ Configuration

### config.yml

The plugin uses a comprehensive configuration system with customizable messages and settings:

```yaml
messages:
  prefix: "&8[&bRollerite&8] &r"
  no-permission: "&cYou don't have permission to use this command!"
  # ... more messages

settings:
  tpa-timeout: 60 # seconds
  tpa-cooldown: 10 # seconds
  god-hunger: false # Whether god mode prevents hunger loss
```

### Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `rollerite.gamemode` | Change game modes | `op` |
| `rollerite.god` | Toggle god mode | `op` |
| `rollerite.openinv` | Open other inventories | `op` |
| `rollerite.enderchest` | Open ender chests | `op` |
| `rollerite.fix` | Repair items | `op` |
| `rollerite.tpa` | Teleport requests | `true` |
| `rollerite.trash` | Use trash GUI | `true` |

## 🏗️ Architecture

The plugin follows modern Java development practices:

### Package Structure
```
com.rollerite.plugin/
├── commands/          # Command implementations with tab completion
├── services/          # Business logic services
├── listeners/         # Event listeners
├── models/           # Data models
├── utils/            # Utility classes
└── gui/              # GUI components
```

### Design Patterns
- **Service Layer Pattern**: Business logic separated into services
- **Command Pattern**: Each command is a separate class
- **Observer Pattern**: Event-driven architecture with listeners
- **Singleton Pattern**: Plugin instance management
- **Factory Pattern**: GUI creation

### Key Classes
- `RolleritePlugin`: Main plugin class
- `ConfigService`: Configuration management
- `MessageService`: Message formatting and sending
- `TPAService`: Teleport request management
- `BaseCommand`: Abstract command base class with tab completion
- `CommandUtils`: Common command utilities

## 🚀 Installation

### Prerequisites

- **Minecraft Server**: Paper/Spigot 1.21.5 or higher
- **Java**: Version 17 or higher
- **Maven**: For building from source

### Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Rollerite-Application-Plugin.git
   cd Rollerite-Application-Plugin
   ```

2. Build the plugin:
   ```bash
   mvn clean package
   ```

3. Copy the generated JAR file from `target/` to your server's `plugins/` folder

### Installation on Server

1. Stop your Minecraft server
2. Copy the plugin JAR file to the `plugins/` folder
3. Start your server
4. The plugin will generate default configuration files

## 🛠️ Development

### Building
```bash
mvn clean compile    # Compile only
mvn clean test       # Run tests
mvn clean package    # Create JAR file
```

### Code Style
- Comprehensive JavaDoc comments
- Consistent naming conventions
- Proper error handling
- Logging for debugging
- Clean separation of concerns

### Testing
The plugin includes unit tests for core functionality:
```bash
mvn test
```

## 🔧 Troubleshooting

### Common Issues

1. **"Message not found" errors**: Check your `config.yml` file for proper message formatting
2. **Commands not working**: Verify permissions are set correctly
3. **Tab completion not working**: Ensure you have the required permissions
4. **TPA requests not working**: Check if the target player is online

### Debug Information

The plugin logs detailed information at FINE level:
- God mode activations/deactivations
- TPA request lifecycle
- Trash item deletions
- Command executions

## 📝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes with proper documentation
4. Add tests for new functionality
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🆘 Support

For support, please:
1. Check the documentation
2. Search existing issues
3. Create a new issue with detailed information

## 📈 Version History

- **1.0.0**: Initial release with all core features
  - Game mode management
  - God mode system
  - Inventory utilities
  - TPA system with self-targeting
  - Trash GUI with item counting
  - Comprehensive configuration
  - Minecraft 1.21.5 support
  - Full tab completion support
  - Professional error handling

