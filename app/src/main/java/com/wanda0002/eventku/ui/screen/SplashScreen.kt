package com.wanda0002.eventku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wanda0002.eventku.navigation.Screen
import com.wanda0002.eventku.datastore.SessionManager
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    navController: NavController,
    sessionManager: SessionManager
) {

    LaunchedEffect(Unit) {

        val token =
            sessionManager.tokenFlow.first()

        if (token.isNotEmpty()) {

            navController.navigate(
                Screen.Home.route
            ) {
                popUpTo(0)
            }

        } else {

            navController.navigate(
                Screen.Login.route
            ) {
                popUpTo(0)
            }
        }
    }

    Box(
        modifier =
            Modifier.fillMaxSize(),
        contentAlignment =
            Alignment.Center
    ) {

        Column(
            horizontalAlignment =
                Alignment.CenterHorizontally
        ) {

            CircularProgressIndicator()

            Spacer(
                modifier =
                    Modifier.height(16.dp)
            )

            Text(
                text = "EventKu",
                style =
                    MaterialTheme.typography.headlineMedium
            )
        }
    }
}