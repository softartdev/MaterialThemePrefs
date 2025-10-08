# Publication Guide for Kotlin Multiplatform Libraries

This document describes the setup for publishing Kotlin Multiplatform libraries to Maven Central using the modern `vanniktech/gradle-maven-publish-plugin`.

## Overview

The publication setup uses:
- **Plugin**: `com.vanniktech:gradle-maven-publish-plugin:0.34.0`
- **Target**: Maven Central Portal (central.sonatype.com)
- **Convention Plugin**: `convention.publication` applied to all published modules
- **Secret Management**: Encrypted files decrypted during CI/CD

## Project Structure

```
.
├── convention-plugins/
│   ├── build.gradle.kts                           # Plugin dependencies
│   └── src/main/kotlin/
│       └── convention.publication.gradle.kts      # Publication configuration
├── theme/
│   ├── theme-prefs/build.gradle.kts              # Applies convention.publication
│   ├── theme-material/build.gradle.kts           # Applies convention.publication
│   └── theme-material3/build.gradle.kts          # Applies convention.publication
├── .github/
│   ├── secrets/                                   # Encrypted secret files
│   └── workflows/build_publish.yml                # CI/CD workflow
├── local.properties                               # Local secrets (decrypted on CI)
├── 8257B447.gpg                                   # GPG key (decrypted on CI)
└── gradle.properties                              # Project configuration
```

## Convention Plugin Setup

### 1. Plugin Dependency (`convention-plugins/build.gradle.kts`)

```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("com.vanniktech:gradle-maven-publish-plugin:0.34.0")
}
```

### 2. Publication Configuration (`convention-plugins/src/main/kotlin/convention.publication.gradle.kts`)

The convention plugin handles:
- Loading credentials from `local.properties` or environment variables
- Configuring Maven Central publication
- Conditional signing (disabled for `publishToMavenLocal`)
- POM metadata configuration
- Workaround for vanniktech plugin build service cleanup issue

Key features:
```kotlin
// Load properties with defaults to handle missing credentials
val defaultProps = mapOf(
    "signing.keyId" to "",
    "signing.password" to "",
    "signing.secretKeyRingFile" to "",
    "ossrhUsername" to "",
    "ossrhPassword" to "",
    "mavenCentralUsername" to "",
    "mavenCentralPassword" to ""
)

// Workaround for build service cleanup issue
// See: https://github.com/vanniktech/gradle-maven-publish-plugin/issues/1116
ensureGlobalGradleProperties()

mavenPublishing {
    publishToMavenCentral()
    
    // Conditional signing
    if (!isLocalPublication) {
        if (keyId != null && password != null && keyRingFile?.exists() == true) {
            signAllPublications()
        }
    }
    
    // POM metadata
    pom { /* ... */ }
}
```

## Credentials Configuration

### Local Development

Create `local.properties` in the project root:

```properties
# Maven Central Portal credentials (from https://central.sonatype.com/usertoken)
mavenCentralUsername=<your_username>
mavenCentralPassword=<your_password>

# GPG signing configuration
signing.keyId=<last_8_chars_of_key>
signing.password=<gpg_key_password>
signing.secretKeyRingFile=8257B447.gpg
```

### CI/CD (GitHub Actions)

Credentials are encrypted in `.github/secrets/` and decrypted during CI:
- `local.properties.gpg` → `local.properties`
- `8257B447.gpg.gpg` → `8257B447.gpg`

Environment variables can also be used:
```bash
SIGNING_KEYID
SIGNING_PASSWORD
SIGNING_SECRETKEYRINGFILE
MAVENCENTRALUSERNAME
MAVENCENTRALPASSWORD
```

## Gradle Tasks

### Local Publishing (Testing)

```bash
# Publish to local Maven repository (~/.m2)
./gradlew publishToMavenLocal
```

### Maven Central Publishing

```bash
# Publish and release to Maven Central
./gradlew publishAndReleaseToMavenCentral --no-configuration-cache

# Or publish without auto-release
./gradlew publishToMavenCentral
```

### Other Tasks

```bash
# Build all modules
./gradlew build

# Publish all publications to Maven Central repository
./gradlew publishAllPublicationsToMavenCentralRepository
```

## CI/CD Workflow

The GitHub Actions workflow (`.github/workflows/build_publish.yml`) handles:

1. **Secret Decryption**: Decrypt `local.properties` and GPG key
2. **Build**: Run tests and build all platforms
3. **Publication**: Publish to Maven Central
4. **Cleanup**: Clean up decrypted secrets

Key step:
```yaml
- name: Publish and Release to Maven Central
  continue-on-error: true
  run: ./gradlew publishAndReleaseToMavenCentral --no-configuration-cache
```

## Maven Central Credentials

### Getting Credentials

1. **New System** (Recommended): 
   - Visit https://central.sonatype.com/usertoken
   - Generate user token
   - Use `mavenCentralUsername` and `mavenCentralPassword`

2. **Old OSSRH System** (Legacy):
   - Visit https://s01.oss.sonatype.org or https://oss.sonatype.org
   - Generate user token
   - Use `ossrhUsername` and `ossrhPassword`

The plugin automatically detects which system to use based on the credentials provided.

## Published Modules

All three modules are published to Maven Central:
- `io.github.softartdev:theme-prefs`
- `io.github.softartdev:theme-material`
- `io.github.softartdev:theme-material3`

## Platform Support

Each module is published for multiple platforms:
- **Android**: Debug and Release variants
- **Desktop**: JVM
- **iOS**: iosArm64, iosSimulatorArm64, iosX64
- **Web**: wasmJs
- **Common**: Kotlin Multiplatform metadata

## Troubleshooting

### Build Service Cleanup Error

**Issue**: `Failed to stop service 'maven-central-build-service'`

**Solution**: The convention plugin automatically creates `~/.gradle/gradle.properties` with Maven Central credentials. This is a workaround for [issue #1116](https://github.com/vanniktech/gradle-maven-publish-plugin/issues/1116).

### Invalid Token Error

**Issue**: `Upload failed: {"error":{"message":"Invalid token"}}`

**Solution**: 
- Generate new credentials from https://central.sonatype.com/usertoken
- Update `local.properties` with new `mavenCentralUsername` and `mavenCentralPassword`
- Re-encrypt the file for CI

### Signing Errors

**Issue**: `Unable to retrieve secret key from key ring file`

**Solution**: 
- Ensure GPG key file exists in project root
- Verify `signing.secretKeyRingFile` points to correct file (relative to project root)
- Check `signing.keyId` matches the key in the GPG file

### Missing Properties Error

**Issue**: `Cannot get property 'signing.keyId' on extra properties extension`

**Solution**: The convention plugin now sets default empty values for all properties, preventing this error when secrets are encrypted.

## Version Management

Version is configured in `gradle.properties`:
```properties
GROUP=io.github.softartdev
VERSION=0.9.8
```

Increment `VERSION` before each release.

## POM Configuration

The convention plugin automatically configures POM metadata:
- **Name**: Material Theme Preferences
- **Description**: Kotlin Multiplatform library for easy switching Dark/Light Material themes on Compose
- **License**: MIT
- **URL**: https://github.com/softartdev/MaterialThemePrefs
- **Developer**: Artur Babichev (softartdev)

## Migration Notes

### From Old OSSRH to New Maven Central Portal

The project was migrated from:
- **Old**: `io.codearte.gradle.nexus:gradle-nexus-staging-plugin`
- **New**: `com.vanniktech:gradle-maven-publish-plugin:0.34.0`

Benefits:
- Simpler configuration
- Automatic POM generation
- Better Kotlin Multiplatform support
- Automatic repository management
- No need for manual `closeAndRelease` tasks

### Key Changes

1. **Plugin**: Replaced `gradle-nexus-staging-plugin` with `gradle-maven-publish-plugin`
2. **Configuration**: Moved from manual `maven-publish` + `signing` to `mavenPublishing` DSL
3. **Credentials**: Changed property names from `sonatypeUsername/Password` to `mavenCentralUsername/Password`
4. **Tasks**: Use `publishAndReleaseToMavenCentral` instead of `publishAllPublicationsToSonatypeRepository`

## Best Practices

1. **Test Locally**: Always test with `publishToMavenLocal` before publishing to Maven Central
2. **Version Management**: Never republish the same version - increment version for each release
3. **Secret Security**: Keep `local.properties` and GPG keys out of version control
4. **Build First**: Run `./gradlew build` before publishing to ensure everything compiles
5. **Disable Configuration Cache**: Use `--no-configuration-cache` for publication tasks
6. **Clean Deployments**: Drop failed deployments at https://central.sonatype.com/publishing/deployments

## References

- [Vanniktech Maven Publish Plugin](https://github.com/vanniktech/gradle-maven-publish-plugin)
- [JetBrains KMP Publishing Guide](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-publish-libraries.html)
- [Maven Central Portal](https://central.sonatype.com/)
- [Sonatype OSSRH EOL Notice](https://central.sonatype.org/pages/ossrh-eol/)
- [Build Service Issue #1116](https://github.com/vanniktech/gradle-maven-publish-plugin/issues/1116)

## Quick Start for New Projects

1. Copy `convention-plugins/` directory to your project
2. Add to `settings.gradle.kts`:
   ```kotlin
   includeBuild("convention-plugins")
   ```
3. Apply to module's `build.gradle.kts`:
   ```kotlin
   plugins {
       id("convention.publication")
   }
   ```
4. Create `local.properties` with credentials
5. Generate GPG key and add to project root
6. Update POM metadata in `convention.publication.gradle.kts`
7. Set `GROUP` and `VERSION` in `gradle.properties`
8. Run `./gradlew publishToMavenLocal` to test
9. Run `./gradlew publishAndReleaseToMavenCentral` to publish

## Support

For issues:
- Check the [vanniktech plugin issues](https://github.com/vanniktech/gradle-maven-publish-plugin/issues)
- Review [Maven Central documentation](https://central.sonatype.org/publish/)
- Consult [JetBrains KMP guide](https://www.jetbrains.com/help/kotlin-multiplatform-dev/)

