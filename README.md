# DiscordLocalHooks

<img src="https://slocraft.eu/slocraft-logo-512.png" width=124 height=124 align="right"/>

DiscordLocalHooks was developed for use on the [SloCraft](https://slocraft.eu) network.

Please keep in mind that the plugin has not been updated since May 2022.

### Description

The plugin provides features that allow server administrators to receive notifications on their Discord server when specific events occur on the Minecraft server, 
such as a failing to log in due to incorrect credentials.

### Features

- Integration with AuthMe _(removed after we switched to premium only)_
  - Listen to wrong password events on players with `authme.staffnotify` (usually meant for staff) and notify the configured Discord channel.
- Store command which can be used to send donation notifications to a Discord channel.
- Log command which can be configured with an anti-cheat plugin to notify a Discord channel when cheating is detected.

### Dependencies

DiscordLocalHooks requires a PaperMC server version 1.18.2 or higher (not tested).

### Configuration

On startup a configuration file is loaded: `config.yml`. This file can be found in the plugin's data folder.

- `config.yml` contains settings for the plugin and language strings.

### Usage

  - `/dlp store <color> <message>` - Sends a store purchase notification Discord embed with the specified color and message.
  - `/dlp log <color> <player> <message>` - Sends an anti-cheat notification Discord embed with the specified color and message.
  - `/dlp reload` - Reloads the plugin's configuration files. 

### Permissions

- `dlh.admin` - Allows the player to use admin commands.
- `dlh.store` - Allows the player to use the store command.
- `authme.staffnotify` - Marks the player as monitored for wrong passwords.
