# Changelog

All notable changes to this project are documented in this file.

Release notes are based on git tags and non-merge commit history.

## [1.0.0] - 2026-03-01

- Added Dokka API documentation.
- Enabled Binary Compatibility Validator for theme modules
- Updated AGP, Kotlin, and dependency versions in the version catalog.

## [0.9.9] - 2026-02-12

- Refactored project modules and migrated dependency management to Version Catalog.

## [0.9.8] - 2025-10-01

- Enhanced CI/CD to deploy the web demo and standardized Gradle setup actions.
- Switched CI/CD JDK distribution from Temurin to Oracle.
- Upgraded Kotlin, Gradle, AGP, and supporting dependencies.
- Migrated publishing to the vanniktech maven-publish plugin.

## [0.9.7] - 2025-06-16

- Added Web (Wasm JS) target support and aligned theme preference handling across platforms.

## [0.9.6] - 2025-06-03

- Added an image-to-GIF conversion utility and adjusted related build configuration.

## [0.9.5] - 2025-04-01

- Improved the scroll indicator UX by switching to a linear progress-style indicator.
- Updated Compose stack: compose 1.8.0-beta01.

## [0.9.4] - 2025-03-22

- Updated toolchain and framework versions: kotlin 2.1.20.
- Implemented edge-to-edge support for Material 2 and Material 3 in both dark and light themes.

## [0.9.3] - 2025-03-20

- Enabled explicit API mode for better public API discipline.
- Updated dependencies, improved insets handling, and upgraded Compose to 1.7.3.
- Updated GitHub Actions workflows and CI automation.

## [0.9.2] - 2025-02-05

- Fixed sample behavior when switching Material design mode on iOS.
- Updated toolchain and framework versions: kotlin 2.1.10, gradle 8.12.1, agp 8.8.0.

## [0.9.0] - 2024-10-10

- Updated toolchain and framework versions: kotlin 2.0.21.

## [0.8.9] - 2024-08-23

- Updated toolchain and framework versions: kotlin 2.0.20.

## [0.8.8] - 2024-08-07

- Updated toolchain and framework versions: Kotlin 2.0.10.

## [0.8.7] - 2024-08-05

- Integrated a navigation library into the sample flow.

## [0.8.6] - 2024-07-26

- Expanded and standardized multiplatform resource usage.

## [0.8.3] - 2024-06-12

- Updated library dependencies and build tooling versions.

## [0.8.1] - 2024-05-26

- Updated toolchain and framework versions: kotlin 1.9.24.

## [0.8.0] - 2024-05-26

- Updated moko-resources dependency and resource pipeline: moko-resources 0.24.0-beta-5.

## [0.7.9] - 2024-05-21

- Updated toolchain and framework versions: kotlin 2.0.0, compose 1.6.10.

## [0.7.8] - 2024-05-17

- Updated moko-resources dependency and resource pipeline: moko-resources 0.24.0-beta-4.

## [0.7.7] - 2024-05-17

- Updated Compose stack: compose 1.6.10-rc03.

## [0.7.6] - 2024-05-14

- Updated toolchain and framework versions: kotlin 2.0.0-RC3, compose 1.6.10-rc02.

## [0.7.4] - 2024-05-05

- Fixed a Compose compilation issue in theme components.

## [0.7.3] - 2024-05-04

- Updated moko-resources dependency and resource pipeline: moko_resources 0.24.0-beta-3.

## [0.7.2] - 2024-05-04

- Updated moko-resources dependency and resource pipeline: moko_resources 0.24.0-beta-3.
- Removed temporary publication-signing workaround and restored the standard signing flow.

## [0.7.1] - 2024-05-04

- Updated project version metadata for this release.

## [0.6.9] - 2024-05-02

- Updated toolchain and framework versions: kotlin 2.0.0-RC2, compose 1.6.10-beta03, moko_resources 0.24.0-beta-2, agp 8.4.0.

## [0.6.8] - 2024-04-26

- Updated toolchain and framework versions: kotlin 2.0.0-RC1, compose 1.6.10-beta02, moko-resources 0.24.0-beta-1.

## [0.6.5] - 2024-03-14

- Updated toolchain and framework versions: kotlin 1.9.23, compose 1.6.1.

## [0.6.4] - 2024-02-29

- Updated Compose stack: compose 1.6.0, moko-resources 0.24.0-alpha-5.

## [0.6.3] - 2024-01-27

- Updated moko-resources dependency and resource pipeline: moko-resources 0.23.0.
- Updated toolchain and framework versions: kotlin 1.9.22, compose 1.6.0-beta01.

## [0.6.2] - 2024-01-22

- Updated moko-resources dependency and resource pipeline: moko-resources 0.24.0-alpha-2.
- Applied build system fixes to restore successful project compilation.

## [0.6.1] - 2023-12-28

- Adopted the default Kotlin Multiplatform source-set hierarchy template.
- Updated toolchain and framework versions: kotlin 1.9.21, compose 1.5.11.

## [0.6.0] - 2023-11-01

- Updated toolchain and framework versions: kotlin 1.9.20, compose 1.5.10.
- Removed obsolete experimental/workaround code from the project.

## [0.5.9] - 2023-10-08

- Updated toolchain and framework versions: kotlin 1.8.22, compose 1.5.3.

## [0.5.8] - 2023-09-06

- Updated project documentation in README.
- Updated toolchain and framework versions: kotlin 1.9.10, compose 1.5.1.

## [0.5.7] - 2023-09-01

- Updated project documentation in README.
- Updated library dependencies and build tooling versions.

## [0.5.6] - 2023-08-21

- Refactored sample modules to improve separation and maintainability.
- Added support for both Material Design 2 and Material Design 3.

## [0.5.5] - 2023-08-19

- Updated toolchain and framework versions: kotlin 1.8.22, compose 1.5.0-rc01.
- Migrated theming implementation toward Material 3 APIs.

## [0.5.4] - 2023-08-12

- Updated toolchain and framework versions: kotlin 1.8.22, compose 1.5.0-beta02, androidSDK 34.

## [0.5.3] - 2023-08-12

- Updated toolchain and framework versions: kotlin 1.9.0, compose 1.5.0-beta02, androidSDK 34.

## [0.5.2] - 2023-08-05

- Updated toolchain and framework versions: kotlin 1.8.22, compose 1.5.0-beta01.

## [0.5.1] - 2023-04-20

- Updated library dependencies and build tooling versions.
- Fixed JAR signing during publication.

## [0.5.0] - 2023-04-13

- Updated library dependencies and build tooling versions.

## [0.4.9] - 2023-03-28

- Fixed resource integration and usage issues across modules.

## [0.4.8] - 2023-03-27

- Updated library dependencies and build tooling versions.

## [0.4.7] - 2023-03-08

- Updated library dependencies and build tooling versions.

## [0.4.6] - 2023-02-24

- Updated library dependencies and build tooling versions.

## [0.4.5] - 2023-02-19

- Fixed dialog behavior on iOS.
- Adjusted build scripts for Gradle 8 compatibility.
- Temporarily downgraded Gradle to keep the build stable.

## [0.4.4] - 2023-01-31

- Updated library dependencies and build tooling versions.

## [0.4.3] - 2023-01-22

- Updated project documentation in README.
- Updated library dependencies and build tooling versions.

## [0.4.2] - 2022-12-15

- Updated library dependencies and build tooling versions.

## [0.4.1] - 2022-12-05

- Updated library dependencies and build tooling versions.

## [0.4.0] - 2022-10-12

- Updated library dependencies and build tooling versions.

## [0.3.9] - 2022-09-17

- Updated JetBrains Compose version to v1.2.0-beta01.

## [0.3.8] - 2022-08-28

- Added remaining iOS targets to complete Apple platform coverage.

## [0.3.7] - 2022-08-25

- Updated JetBrains Compose version to v1.2.0-alpha01-dev770 & Kotlin 1.7.10.

## [0.3.6] - 2022-08-20

- Adjusted Kotlin/Native cache configuration for build stability.
- Updated library dependencies and build tooling versions.
- Raised Android compile and target SDK levels to 33.

## [0.3.5] - 2022-07-31

- Reworked temporary icon asset ownership to prevent packaging issues.

## [0.3.4] - 2022-07-30

- Updated JetBrains Compose version to 1.2.0-alpha01-dev731.
- Updated Kotlin to 1.7.10 and refreshed other library dependencies.
- Updated JetBrains Compose version to 1.2.0-alpha01-dev753.
- Updated project version metadata for this release.

## [0.3.3] - 2022-05-16

- Updated moko-resources integration and related APIs.
- Added an Xcode project for iOS sample integration.
- Introduced a temporary iOS sample kexe module for Compose integration.
- Linked the Xcode project to the iOS sample kexe module.
- Prepared project structure for Compose Multiplatform adoption.
- Removed stray macOS metadata files from version control.
- Updated project version metadata for this release.

## [0.3.2] - 2022-05-07

- Prepared JetBrains Compose setup for iOS target builds.
- Enabled and validated JetBrains Compose execution on iOS.

## [0.3.1] - 2022-05-04

- Updated JetBrains Compose version to alpha.

## [0.3] - 2022-04-30

- Updated dependency versions across project modules.

## [0.2] - 2022-02-16

- Updated project documentation in README.
- Added the project license file.
- Changed Android storage to use a dedicated preferences file.

## [0.1] - 2021-12-28

- Initial project scaffold for multiplatform theme preferences.
- Updated Gradle configuration and wrapper setup.
- Updated Compose Multiplatform dependencies and related setup.
- Renamed package structure for clearer library namespace.
- Integrated moko-resources for multiplatform resource access.
- Added an initial sample application.
- Implemented interactive click actions in theme preference UI.
- Moved sample app code into a shared common module.
- Performed internal refactoring to improve code structure.
- Added helper utilities for preference persistence.
- Renamed Gradle modules to match the new project layout.
- Renamed Java/Kotlin package paths for consistency.
- Introduced a shared umbrella module for sample code.
- Exposed theme preferences through CompositionLocal for scoped access.
- Updated project documentation in README.
- Added screenshots to project documentation.
- Added support for publishing artifacts to Maven Local for testing.
- Added publication support for the Android target artifact.
- Updated library dependencies and build tooling versions.
- Set up Maven publication configuration for the library modules.
- Configured CI/CD to run with JDK 11.
- Automated Nexus staging close-and-release publication flow.
- Fixed CI/CD tag detection and signing configuration.
- Fixed CI/CD branch handling for release and publish workflows.
