package com.wanda0002.eventku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wanda0002.eventku.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val isLoading by
    viewModel.isLoading.collectAsState()

    val loginSuccess by
    viewModel.loginSuccess.collectAsState()

    val errorMessage by
    viewModel.errorMessage.collectAsState()

    LaunchedEffect(loginSuccess) {

        if (loginSuccess) {

            navController.navigate(
                com.wanda0002.eventku.navigation.Screen.Home.route
            ) {

                popUpTo("login") {
                    inclusive = true
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),

        verticalArrangement =
            Arrangement.Center
    ) {

        Text(
            text = "🎉 EventKu",
            style =
                MaterialTheme.typography.displaySmall
        )

        Text(
            text = "Kelola Event Lebih Mudah"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        ElevatedCard(
            modifier =
                Modifier.fillMaxWidth()
        ) {

            Column(
                modifier =
                    Modifier.padding(20.dp)
            ) {

                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                    },
                    label = {
                        Text("Email")
                    },
                    modifier =
                        Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                    },
                    label = {
                        Text("Password")
                    },
                    modifier =
                        Modifier.fillMaxWidth()
                )

                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )

                Button(
                    onClick = {
                        viewModel.login(
                            email,
                            password
                        )
                    },
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    if (isLoading) {

                        CircularProgressIndicator()

                    } else {

                        Text("Login")
                    }
                }
            }
        }
    }
}