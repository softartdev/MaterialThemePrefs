import java.util.Properties

plugins {
    id("com.vanniktech.maven.publish")
}

// Load properties from local.properties or set defaults
val secretPropsFile = project.rootProject.file("local.properties")
val props = Properties()

if (secretPropsFile.exists()) {
    secretPropsFile.reader().use(props::load)
}
// Set all properties with defaults to avoid "property does not exist" errors
val defaultProps = mapOf(
    "signing.keyId" to "",
    "signing.password" to "",
    "signing.secretKeyRingFile" to "",
    "ossrhUsername" to "",
    "ossrhPassword" to "",
    "mavenCentralUsername" to "",
    "mavenCentralPassword" to ""
)

defaultProps.forEach { (key, defaultValue) ->
    val value = props.getProperty(key) ?: System.getenv(key.uppercase().replace(".", "_")) ?: defaultValue
    ext[key] = value
    project.rootProject.ext.set(key, value)
    // Set system properties for Maven Central and OSSRH credentials
    if (key in listOf("mavenCentralUsername", "mavenCentralPassword", "ossrhUsername", "ossrhPassword") && value.isNotEmpty()) {
        System.setProperty(key, value)
    }
}

fun getExtraString(name: String) = ext[name]?.toString()?.takeIf(String::isNotEmpty)

// Workaround for vanniktech plugin build service cleanup issue
fun ensureGlobalGradleProperties() {
    val globalGradleDir = File(System.getProperty("user.home"), ".gradle")
    val globalGradleProps = File(globalGradleDir, "gradle.properties")
    
    val mavenCentralUsername = getExtraString("mavenCentralUsername")
    val mavenCentralPassword = getExtraString("mavenCentralPassword")
    
    if (mavenCentralUsername != null && mavenCentralPassword != null) {
        if (!globalGradleDir.exists()) {
            globalGradleDir.mkdirs()
        }
        val existingProps = Properties()
        if (globalGradleProps.exists()) {
            globalGradleProps.reader().use(existingProps::load)
        }
        var needsUpdate = false
        if (existingProps.getProperty("mavenCentralUsername") != mavenCentralUsername) {
            existingProps.setProperty("mavenCentralUsername", mavenCentralUsername)
            needsUpdate = true
        }
        if (existingProps.getProperty("mavenCentralPassword") != mavenCentralPassword) {
            existingProps.setProperty("mavenCentralPassword", mavenCentralPassword)
            needsUpdate = true
        }
        if (needsUpdate) {
            globalGradleProps.writer().use { writer ->
                existingProps.store(writer, "Updated by MaterialThemePrefs build script")
            }
            println("Updated global gradle.properties with Maven Central credentials")
        }
    }
}

ensureGlobalGradleProperties() // FIXME https://github.com/vanniktech/gradle-maven-publish-plugin/issues/1116

mavenPublishing {
    // Configure Maven Central publication
    publishToMavenCentral()

    // Only sign publications when not publishing to Maven Local and signing credentials are available
    val isLocalPublication = gradle.startParameter.taskNames.any { taskName ->
        taskName.contains("publishToMavenLocal") || taskName.contains("ToMavenLocal")
    }
    if (!isLocalPublication) {
        val keyId = getExtraString("signing.keyId")
        val password = getExtraString("signing.password")
        val keyRingFile = getExtraString("signing.secretKeyRingFile")?.let(rootProject::file)
        
        if (keyId != null && password != null && keyRingFile?.exists() == true) {
            // Set signing properties for vanniktech plugin
            project.ext.set("signing.keyId", keyId)
            project.ext.set("signing.password", password)
            project.ext.set("signing.secretKeyRingFile", keyRingFile.absolutePath)
            // Also set system properties as backup
            System.setProperty("signing.keyId", keyId)
            System.setProperty("signing.password", password)
            System.setProperty("signing.secretKeyRingFile", keyRingFile.absolutePath)
            
            signAllPublications()
        }
    }
    // Configure POM metadata for all publications
    pom {
        name.set("Material Theme Preferences")
        description.set("Kotlin Multiplatform library for easy switching Dark/Light Material themes on Compose.")
        inceptionYear.set("2024")
        url.set("https://github.com/softartdev/MaterialThemePrefs")
        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
                distribution.set("repo")
            }
        }
        developers {
            developer {
                id.set("softartdev")
                name.set("Artur Babichev")
                email.set("artik222012@gmail.com")
                url.set("https://github.com/softartdev")
            }
        }
        scm {
            url.set("https://github.com/softartdev/MaterialThemePrefs")
            connection.set("scm:git:git://github.com/softartdev/MaterialThemePrefs.git")
            developerConnection.set("scm:git:ssh://git@github.com/softartdev/MaterialThemePrefs.git")
        }
    }
}
