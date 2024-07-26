import java.util.*

plugins {
    `maven-publish`
    signing
    id("io.codearte.nexus-staging")
}

// Stub secrets to let the project sync and build without the publication values set up
ext["signing.keyId"] = null
ext["signing.password"] = null
ext["signing.secretKeyRingFile"] = null
ext["ossrhToken"] = null
ext["ossrhTokenPassword"] = null
ext["sonatypeStagingProfileId"] = null

// Grabbing secrets from local.properties file or from environment variables, which could be used on CI
val secretPropsFile = project.rootProject.file("local.properties")
if (secretPropsFile.exists()) {
    secretPropsFile.reader().use {
        Properties().apply {
            load(it)
        }
    }.onEach { (name, value) ->
        ext[name.toString()] = value
    }
} else {
    ext["signing.keyId"] = System.getenv("SIGNING_KEY_ID")
    ext["signing.password"] = System.getenv("SIGNING_PASSWORD")
    ext["signing.secretKeyRingFile"] = System.getenv("SIGNING_SECRET_KEY_RING_FILE")
    ext["ossrhToken"] = System.getenv("OSSRH_TOKEN")
    ext["ossrhTokenPassword"] = System.getenv("OSSRH_TOKEN_PASSWORD")
    ext["sonatypeStagingProfileId"] = System.getenv("SONATYPE_STAGING_PROFILE_ID")
}
ext["signing.secretKeyRingFile"] = "../../${ext["signing.secretKeyRingFile"]}" // path from module

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

fun getExtraString(name: String) = ext[name]?.toString()

publishing {
    // Configure maven central repository
    repositories {
        maven {
            name = "sonatype"
            setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = getExtraString("ossrhToken")
                password = getExtraString("ossrhTokenPassword")
            }
        }
    }

    // Configure all publications
    publications.withType<MavenPublication> {

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        // Provide artifacts information requited by Maven Central
        pom {
            name.set("Material Theme Preferences")
            description.set("Kotlin Multiplatform library for easy switching Dark/Light Material themes on Compose.")
            url.set("https://github.com/softartdev/MaterialThemePrefs")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("softartdev")
                    name.set("Artur Babichev")
                    email.set("artik222012@gmail.com")
                }
            }
            scm {
                url.set("https://github.com/softartdev/MaterialThemePrefs")
            }

        }
    }
}

signing {
    val isLocalPublication = gradle.startParameter.taskNames.any { taskName ->
        taskName.contains("publishToMavenLocal")
    }
    if (!isLocalPublication) sign(publishing.publications)
}

nexusStaging {
    serverUrl = "https://s01.oss.sonatype.org/service/local/"
    packageGroup = project.property("GROUP").toString()
    stagingProfileId = getExtraString("sonatypeStagingProfileId")
    username = getExtraString("ossrhToken")
    password = getExtraString("ossrhTokenPassword")
}