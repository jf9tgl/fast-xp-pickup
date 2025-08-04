# FastXPPickup

[日本語](README_ja.md)

A Minecraft plugin that removes or makes configurable the annoying delay when collecting experience orbs. Designed to improve performance and player experience.

## Features

- ✨ Completely remove or configure XP orb pickup delays
- 🎯 Dynamic delay calculation based on XP orb values

## Installation

1. Copy the plugin file (`FastXPPickup.jar`) to your server's `plugins` folder
2. Restart the server or reload plugins
3. `plugins/FastXPPickup/config.yml` will be automatically generated

## Configuration

### config.yml

```yaml
# Base delay for XP orb pickup (in ticks, 20 ticks = 1 second)
# Set to 0 to disable delay completely
base-pickup-delay: 0

# Enable value-based delay calculation based on XP orb values
value-based-delay:
  enabled: false              # Enable/disable
  delay-per-xp-point: 0.1    # Delay per XP point (in ticks)
  max-delay: 20              # Maximum delay cap (in ticks)
  min-delay: 0               # Minimum delay floor (in ticks)
```

### Configuration Examples

#### Basic Setup (No Delay)
```yaml
base-pickup-delay: 0
value-based-delay:
  enabled: false
```

#### Fixed Delay Setup
```yaml
base-pickup-delay: 5  # 5 tick delay for all XP orbs
value-based-delay:
  enabled: false
```

#### Dynamic Delay Setup
```yaml
base-pickup-delay: 0
value-based-delay:
  enabled: true
  delay-per-xp-point: 0.2  # Add 0.2 ticks per XP point
  max-delay: 40           # Maximum 40 ticks
  min-delay: 2            # Minimum 2 ticks
```

## Commands

### `/fastxp reload`
Reload the configuration file.

**Permission**: `fastxppickup.admin` (Default: OP)

### `/fastxpp config`
Display the contents of the configuration file.

**Permission**: `fastxppickup.admin` (Default: OP)

## Permissions

| Permission | Description | Default     |
|------------|-------------|-------------|
| `fastxppickup.pickup` | Allow picking up XP orbs with configurable delay | All players |
| `fastxppickup.use` | Use the `/fastxp` command | OP          |
| `fastxppickup.admin` | Permission to reload configuration | OP          |

## How It Works

This plugin hooks into the `PlayerExpCooldownChangeEvent` to control XP orb pickup delays.

1. Player attempts to collect an XP orb
2. Detect nearby XP orb values
3. Calculate delay based on configuration
4. Apply the calculated delay

### Delay Calculation Logic

```
If value-based delay is enabled:
delay = XP orb value × delay-per-xp-point
delay = Math.max(delay, min-delay)
delay = Math.min(delay, max-delay)

If value-based delay is disabled:
delay = base-pickup-delay
```

## Compatibility

- **Minecraft**: 1.19+
- **Server Software**: Bukkit/Spigot/Paper

## FAQ

### Q: What about performance impact?
A: This plugin is lightweight and only operates during XP orb collection events. It will not interfere with normal server operations.

### Q: Conflicts with other XP-related plugins?
A: Generally, no conflicts occur, but there may be compatibility issues with other plugins that also control XP orb pickup delays.

## Troubleshooting

### Configuration not taking effect
1. Use `/fastxp reload` command to reload configuration
2. Check if `config.yml` syntax is correct
3. Check server logs for errors

### Permissions not working
1. Check your permission plugin (LuckPerms, etc.) configuration
2. Verify access using the `/fastxp` command

## Building from Source

This project uses Gradle as its build system.

```bash
# Clone the repository
git clone <repository-url>
cd fast-xp-pickup

# Build the plugin
./gradlew build

# The compiled JAR will be in build/libs/
```

## Developer Information

- **Author**: exstrasilly (xnnie)
- **Version**: 1.0.0
- **Contact**: 
  - Discord: @xxxxxxxxxnnie
  - Telegram: @xxxxxxxxxnnie
  - Fiverr: @exstramc

## Contributing

Bug reports and feature requests are welcome. Please follow the existing code style when submitting pull requests.

### Development Setup
1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

---

**Note**: It is recommended to backup your server before using this plugin.
