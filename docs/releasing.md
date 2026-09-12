# Release process

Releases are built and published by [`.github/workflows/publish.yml`](../.github/workflows/publish.yml). The workflow is manual and defaults to a non-publishing dry run.

## Version format

`gradle.properties` is the source of truth for `minecraft_version`, `mod_version`, and `archives_name`.

For Minecraft `1.20.1` and mod version `2.0.0`, the workflow derives:

```text
Mod metadata:       2.0.0
Git tag:            1.20.1-2.0.0
GitHub release:     1.20.1-2.0.0
Fabric JAR:         create-dyn-light-Fabric-1.20.1-2.0.0.jar
Forge JAR:          create-dyn-light-Forge-1.20.1-2.0.0.jar
Fabric platform ID: Fabric-1.20.1-2.0.0
Forge platform ID:  Forge-1.20.1-2.0.0
```

Stable SemVer produces a release. `-alpha.*` produces an alpha prerelease, while `-beta.*` and `-rc.*` produce beta prereleases.

## Repository configuration

GitHub Releases use the workflow's `GITHUB_TOKEN`. Publishing to mod platforms additionally requires:

| Kind | Name | Expected value |
| --- | --- | --- |
| Repository variable | `MODRINTH_ID` | `vfr63NCk` |
| Repository variable | `CURSEFORGE_ID` | `1692297` |
| Repository secret | `MODRINTH_TOKEN` | A token with permission to create versions |
| Repository secret | `CURSEFORGE_TOKEN` | A CurseForge upload token |

The project IDs must identify this fork's projects, not the original mod's projects. Token values must not be committed or printed.

The CurseForge and Modrinth project pages may remain unavailable while the projects are drafts or awaiting moderation. Add public download links and badges to the README only after both pages are accessible without authentication.

## Prepare a release

1. Update `mod_version` in `gradle.properties`.
2. Add a non-empty matching section such as `## [2.0.0]` to `CHANGELOG.md`.
3. Build with JDK 21 using `./gradlew clean build --no-daemon`.
4. Confirm the Fabric and Forge JARs are present and normal CI passes for the release commit.
5. Commit and push the release preparation to the `1.20.1` branch.
6. Confirm the intended `1.20.1-<mod version>` tag does not already exist.

The build runs on JDK 21 because Loom requires it, but validates Java 17 bytecode for the published mod.

## Dry run

Run the **Publish Jar** workflow from the Actions tab with `dry_run` enabled. A dry run:

- builds both loaders;
- validates embedded versions, Java bytecode, licenses, and JAR contents;
- prepares release notes and SHA-256 hashes;
- uploads a short-lived `release-candidates-<version>` workflow artifact;
- does not create tags, releases, or external platform versions.

Inspect the workflow artifact and verify that it contains exactly one Fabric JAR, one Forge JAR, `release-notes.md`, and `SHA256SUMS`.

When running the workflow locally with `act`, release candidates are still built and validated under `dist/`, but artifact upload is skipped because the local runner does not provide GitHub's artifact-service token.

## Publish

Run **Publish Jar** again from the `1.20.1` branch with `dry_run` disabled. Select at least one destination and the loaders to publish:

- `publish_github_release` creates one GitHub release containing both loader JARs.
- `publish_to_modrinth` creates one loader-specific version per selected loader.
- `publish_to_curseforge` creates one loader-specific version per selected loader.

Platform uploads mark the mod as usable on either the client or server and recommended on both. Client installation enables smooth visual dynamic lights, while server installation enables optional vanilla light blocks.

Do not rerun a partially successful publication without first checking which tag, GitHub release, Modrinth versions, or CurseForge files already exist.
