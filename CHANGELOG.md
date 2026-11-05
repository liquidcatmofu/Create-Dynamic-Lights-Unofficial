# Changelog

All notable changes to Create: Dynamic Lights (Unofficial) are documented in this file.

The project follows [Semantic Versioning](https://semver.org/). Release tags combine the Minecraft version and mod version, such as `1.20.1-2.0.0`.

## [Unreleased]

## [2.1.0]

### Added

- Added optional Forge integration with Cheap Lighting 1.3+, including backend mode toggles and renderer reload recovery.

### Changed

- Forge selects dynamic-light backends in this order: Sodium Dynamic Lights, RyoamicLights, then Cheap Lighting. Client dynamic-light events are disabled when no backend is installed.
- Added Forge development client options for Cheap Lighting and for running without a dynamic-light backend.

## [2.0.0]

### Added

- Added Fabric and Forge integration with Sodium Dynamic Lights.
- Added Fabric and Forge compatibility with RyoamicLights 0.2.3 as an alternative.
- Added release builds for Minecraft 1.20.1 and Create 6.0.x.

### Changed

- Updated Create integration to the Create 6 API and Catnip packages.
- Standardized release versions and loader-specific JAR names.
- Updated CI and release validation for reproducible Fabric and Forge artifacts.

### Fixed

- Prevented failures while removing contraption light sources when contraption data is unavailable.
- Corrected Fabric's packaged license metadata to MIT.

## [1.0.2]

### Fixed

- Fixed [original issue #6](https://github.com/leon-o/Create-Dynamic-Lights/issues/6), a crash in `CreateDynLightSourceHolder.removeAll`.
