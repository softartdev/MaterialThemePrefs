plugins {
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.dokka)
}

dependencies {
    dokka(projects.theme.themePrefs)
    dokka(projects.theme.themeMaterial)
    dokka(projects.theme.themeMaterial3)
}

dokka {
    dokkaPublications.html {
        outputDirectory.set(layout.buildDirectory.dir("site/docs"))
    }
}
