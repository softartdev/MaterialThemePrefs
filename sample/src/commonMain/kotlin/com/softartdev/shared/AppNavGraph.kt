package com.softartdev.shared

import kotlinx.serialization.Serializable

sealed interface AppNavGraph {

    @Serializable
    data object Settings : AppNavGraph

    @Serializable
    data object NoteDetail : AppNavGraph

    @Serializable
    data object ThemeDialog : AppNavGraph

    companion object {

        fun from(route: String?): AppNavGraph = when (route) {
            Settings::class.qualifiedName -> Settings
            NoteDetail::class.qualifiedName -> NoteDetail
            ThemeDialog::class.qualifiedName -> ThemeDialog
            else -> Settings
        }
    }
}

sealed interface DesignNavGraph {

    @Serializable
    data object MaterialApp : DesignNavGraph

    @Serializable
    data object Material3App : DesignNavGraph
}