package com.wanda0002.eventku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.wanda0002.eventku.data.EventDatabase
import com.wanda0002.eventku.navigation.NavGraph
import com.wanda0002.eventku.repository.EventRepository
import com.wanda0002.eventku.viewmodel.EventViewModel
import com.wanda0002.eventku.viewmodel.EventViewModelFactory
import com.wanda0002.eventku.datastore.SessionManager
import com.wanda0002.eventku.repository.AuthRepository
import com.wanda0002.eventku.viewmodel.LoginViewModel
import com.wanda0002.eventku.viewmodel.LoginViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database =
            EventDatabase.getDatabase(this)

        val repository =
            EventRepository(
                database.eventDao()
            )

        val sessionManager =
            SessionManager(this)

        setContent {

            val viewModel: EventViewModel =
                viewModel(
                    factory =
                        EventViewModelFactory(
                            repository
                        )
                )

            val loginViewModel: LoginViewModel =
                viewModel(
                    factory =
                        LoginViewModelFactory(
                            AuthRepository(),
                            sessionManager
                        )
                )

            val navController =
                rememberNavController()

            NavGraph(
                navController = navController,
                eventViewModel = viewModel,
                loginViewModel = loginViewModel,
                sessionManager = sessionManager
            )
        }
    }
}