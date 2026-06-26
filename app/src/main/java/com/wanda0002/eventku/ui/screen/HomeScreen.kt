package com.wanda0002.eventku.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.wanda0002.eventku.navigation.Screen

import com.wanda0002.eventku.viewmodel.EventViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: EventViewModel
) {

    val events by viewModel.events.collectAsState()

    val isLoading by viewModel.isLoading.collectAsState()


    var searchQuery by remember {
        mutableStateOf("")
    }

    LaunchedEffect(Unit) {

        viewModel.syncApiEvents()

        viewModel.syncPendingEvents()
    }

    val filteredEvents =
        events.filter {
                    it.title.contains(
                        searchQuery,
                        ignoreCase = true
                    )
        }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            HeaderCard()

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Button(
                    modifier =
                        Modifier.weight(1f),

                    onClick = {
                        navController.navigate(
                            Screen.Profile.route)
                    }
                ) {

                    Text("👤 Profile")
                }

                Button(
                    modifier = Modifier.weight(1f),

                    onClick = {

                        navController.navigate(
                            Screen.AddEvent.route
                        )
                    }
                ) {

                    Text("➕ Event")
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {

            OutlinedTextField(
                value = searchQuery,

                onValueChange = {
                    searchQuery = it
                },

                modifier =
                    Modifier.fillMaxWidth(),

                label = {
                    Text("Cari event...")
                }
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        item {

            Card(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Column {

                        Text(
                            text = "💾 Data Lokal (Room)",

                            style =
                                MaterialTheme.typography.titleMedium
                        )

                        Text(
                            text =
                                "Event tersimpan di perangkat"
                        )
                    }

                    AssistChip(
                        onClick = {},

                        label = {

                            Text(
                                "${events.size} Event"
                            )
                        }
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )
        }

        if (isLoading) {

            item {

                Box(
                    modifier =
                        Modifier.fillMaxWidth()
                ) {

                    CircularProgressIndicator()
                }

                Spacer(
                    modifier =
                        Modifier.height(16.dp)
                )
            }
        }

        items(filteredEvents) { event ->

            EventCard(

                event = event,

                onEdit = {

                    navController.navigate(
                        Screen.EditEvent.createRoute(
                            event.id
                        )
                    )
                },

                onDelete = {

                    viewModel.delete(event)
                }
            )
        }

        item {

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )

            Card(
                modifier =
                    Modifier.fillMaxWidth()
            ) {

                Row(
                    modifier =
                        Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "📋",

                        style =
                            MaterialTheme.typography.displayMedium
                    )

                    Spacer(
                        modifier =
                            Modifier.width(16.dp)
                    )

                    Column {

                        Text(
                            text = "Tentang EventKu",

                            style =
                                MaterialTheme.typography.titleLarge
                        )

                        Spacer(
                            modifier =
                                Modifier.height(8.dp)
                        )

                        Text(
                            text =
                                "Aplikasi untuk membantu mengelola event dengan mudah, cepat, dan terorganisir."
                        )

                        Text(
                            text =
                                "Semua data tersimpan aman di perangkatmu."
                        )
                    }
                }
            }

            Spacer(
                modifier =
                    Modifier.height(20.dp)
            )
        }
    }
}

@Composable
fun HeaderCard() {

    Card(
        modifier =
            Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Row {

                Text(
                    text = "🎉",

                    style =
                        MaterialTheme.typography.displayMedium
                )

                Spacer(
                    modifier =
                        Modifier.width(12.dp)
                )

                Column {

                    Text(
                        text = "EventKu",

                        style =
                            MaterialTheme.typography.headlineLarge
                    )

                    Text(
                        text =
                            "Kelola event dengan mudah dan cepat ✨"
                    )
                }
            }

            Text(
                text = "👤",

                style =
                    MaterialTheme.typography.headlineMedium
            )
        }
    }
}