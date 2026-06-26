package com.wanda0002.eventku.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wanda0002.eventku.data.Event

@Composable
fun EventCard(
    event: Event,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(20.dp)
    ) {

        Row(
            modifier = Modifier.padding(16.dp)
        ) {

            AsyncImage(
                model =
                    if (event.imageUri.isNotEmpty())
                        event.imageUri
                    else
                        "https://picsum.photos/200",

                contentDescription = null,

                modifier = Modifier
                    .size(100.dp)
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = event.title,
                    style =
                        MaterialTheme.typography.titleLarge
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = event.description
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Text("📅 ${event.date}")

                Text("📍 ${event.location}")
                AssistChip(
                    onClick = {},

                    label = {

                        Text(
                            if(event.isSynced)
                                "Online"
                            else
                                "Pending Sync"
                        )
                    }
                )
            }

            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                IconButton(
                    onClick = onEdit
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Edit,
                        contentDescription = null
                    )
                }

                IconButton(
                    onClick = {
                        showDialog = true
                    }
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }
        }
    }

    if (showDialog) {

        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },

            title = {
                Text("Konfirmasi")
            },

            text = {
                Text(
                    "Yakin ingin menghapus event?"
                )
            },

            confirmButton = {

                Button(
                    onClick = {

                        onDelete()

                        showDialog = false
                    }
                ) {
                    Text("Ya")
                }
            },

            dismissButton = {

                OutlinedButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}