package com.wanda0002.eventku.navigation

sealed class Screen(val route: String) {

    object Login : Screen("login")

    object Home : Screen("home")

    object Profile : Screen("profile")

    object AddEvent : Screen("add_event")

    object Splash : Screen("splash")
    object EditEvent : Screen("edit_event/{id}") {

        fun createRoute(id: Int) =
            "edit_event/$id"
    }
}