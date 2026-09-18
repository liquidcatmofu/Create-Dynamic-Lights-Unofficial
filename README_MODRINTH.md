# Create: Dynamic Lights (Unofficial)

An unofficial maintenance fork of [Create: Dynamic Lights](https://modrinth.com/mod/create-dynamic-lights) for Minecraft 1.20.1 and Create 6.

It adds lighting to moving Create contraptions through smooth client-side dynamic lighting, optional server-side vanilla light blocks, or both. Maintenance is provided on a best-effort basis.

## Features

- Adds smooth, moving light sources to illuminated blocks on Create contraptions.
- Supports Sodium Dynamic Lights and RyoamicLights as alternative client-side backends.
- Can place invisible vanilla light blocks while contraptions move, allowing server-side light to affect mob spawning, snow, and ice.
- Provides configurable luminance scaling and update intervals.
- Supports client-only, server-only, and client-and-server installation.

## Supported environments

| Loader | Minecraft | Create | Dynamic-light backend |
| --- | --- | --- | --- |
| Fabric | 1.20.1 | Create Fabric 6.0.x | Sodium Dynamic Lights 1.0.x or RyoamicLights 0.2.3 |
| Forge | 1.20.1 with Forge 47.x | Create 6.0.x | Sodium Dynamic Lights 1.0.x or RyoamicLights 0.2.3 |

- Java 17 is required.
- Fabric also requires Fabric API.
- Fabric and Forge use separate JARs; install the one matching your loader.
- RyoamicLights requires compatible ObsidianUI and Architectury API versions.
- If both dynamic-light backends are installed, this mod uses Sodium Dynamic Lights.

## Installation sides

| Installation | Available behavior |
| --- | --- |
| Client only | Smooth visual dynamic lighting. No real light blocks are placed in the world. |
| Server only | Optional vanilla light blocks. Clients do not need this mod. |
| Client and server | Both behaviors are available and can be configured independently. |

Without Sodium Dynamic Lights or RyoamicLights, client-side smooth lighting is inactive. The server-side light-block feature remains available.

## Configuration

Client configuration controls dynamic-light integration, luminance scaling, backend-mode delay handling, and update intervals. Server configuration controls vanilla light-block placement and its minimum source luminance.

Configuration keys containing `Lamb` are legacy names retained for compatibility.

## Known limitations

- Client-side dynamic lighting is visual only. It does not affect mob spawning, crop growth, snow, or ice.
- Vanilla light blocks affect the world, but may use more server resources and can visibly lag behind fast contraptions.
- On Fabric, changing and saving server configuration from a client-only installation may cause a configuration mismatch. Installing the mod on the server avoids this issue.

## Source, issues, and license

- [Source code](https://github.com/liquidcatmofu/Create-Dynamic-Lights)
- [Issue tracker](https://github.com/liquidcatmofu/Create-Dynamic-Lights/issues)
- [Changelog](https://github.com/liquidcatmofu/Create-Dynamic-Lights/blob/1.20.1/CHANGELOG.md)
- [MIT License](https://github.com/liquidcatmofu/Create-Dynamic-Lights/blob/1.20.1/LICENSE)

Original project and implementation by Leon / leon-o. Minecraft 1.20.1 and Create 6 maintenance fork by LiquidCatMofu.

This project is not affiliated with or endorsed by the Create team or the original project author.
