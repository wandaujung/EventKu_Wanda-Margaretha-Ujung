package com.wanda0002.eventku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.wanda0002.eventku.repository.AuthRepository
import com.wanda0002.eventku.viewmodel.ProfileViewModel
import com.wanda0002.eventku.viewmodel.ProfileViewModelFactory
import com.wanda0002.eventku.navigation.Screen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import com.wanda0002.eventku.datastore.SessionManager

@Composable
fun ProfileScreen(navController: NavController, sessionManager: SessionManager) {

    val viewModel: ProfileViewModel =
        viewModel(
            factory =
                ProfileViewModelFactory(
                    AuthRepository()
                )
        )

    val user by
    viewModel.user.collectAsState()

    val scope = rememberCoroutineScope()

    val loading by
    viewModel.loading.collectAsState()

    if (loading) {

        Box(
            modifier =
                Modifier.fillMaxSize(),
            contentAlignment =
                Alignment.Center
        ) {

            CircularProgressIndicator()
        }

        return
    }


    if (user == null) {

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = "Data profile belum berhasil dimuat"
            )
        }

    } else {

        user?.let {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = it.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape)
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                Text(
                    text =
                        "${it.firstName} ${it.lastName}",

                    style =
                        MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = it.email
                )

                Spacer(
                    modifier =
                        Modifier.height(32.dp)
                )

                ElevatedCard {

                    Column(
                        modifier =
                            Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "Role"
                        )

                        Text(
                            text = "Administrator"
                        )

                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )

                        Text(
                            text = "Status"
                        )

                        Text(
                            text = "Aktif"
                        )
                    }
                }

                Spacer(
                    modifier =
                        Modifier.height(24.dp)
                )

                Button(
                    onClick = {
                        scope.launch {

                            sessionManager.logout()

                            navController.navigate(
                                Screen.Login.route
                            ) {
                                popUpTo(0)
                            }
                        }
                    }
                ) {

                    Text("Logout")
                }
            }
        }
    }
}