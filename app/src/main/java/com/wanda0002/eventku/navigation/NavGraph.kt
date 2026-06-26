package com.wanda0002.eventku.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.wanda0002.eventku.datastore.SessionManager
import com.wanda0002.eventku.viewmodel.EventViewModel
import com.wanda0002.eventku.viewmodel.LoginViewModel
import com.wanda0002.eventku.ui.screen.*

@Composable
fun NavGraph(
    navController: NavHostController,
    eventViewModel: EventViewModel,
    loginViewModel: LoginViewModel,
    sessionManager: SessionManager
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(
                navController = navController,
                sessionManager = sessionManager
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navController = navController,
                viewModel = loginViewModel
            )
        }

        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                viewModel = eventViewModel
            )
        }

        composable(Screen.AddEvent.route) {
            AddEventScreen(
                navController = navController,
                viewModel = eventViewModel
            )
        }

        composable(Screen.EditEvent.route) { backStack ->
            val id = backStack.arguments?.getString("id")?.toInt() ?: 0
            EditEventScreen(
                navController = navController,
                id = id,
                viewModel = eventViewModel
            )
        }

        composable(Screen.Profile.route) {

            ProfileScreen(
                navController = navController,
                sessionManager = sessionManager
            )
        }
    }
}