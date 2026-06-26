package com.wanda0002.eventku.ui.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.wanda0002.eventku.data.Event
import com.wanda0002.eventku.viewmodel.EventViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEventScreen(
    navController: NavController,
    id: Int,
    viewModel: EventViewModel
) {

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    LaunchedEffect(id) {

        val event =
            viewModel.getEventById(id)

        event?.let {

            title = it.title
            description = it.description
            date = it.date
            location = it.location

            if (it.imageUri.isNotEmpty()) {
                imageUri = Uri.parse(it.imageUri)
            }
        }
    }

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            imageUri = uri
        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Text(
            text = "✏️ Edit Event",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.surfaceVariant
            )
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Judul Event")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it
                    },
                    label = {
                        Text("Deskripsi Event")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    minLines = 3
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = date,
                    onValueChange = {
                        date = it
                    },
                    label = {
                        Text("Tanggal Event")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = location,
                    onValueChange = {
                        location = it
                    },
                    label = {
                        Text("Lokasi Event")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {
                        launcher.launch("image/*")
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("📷 Ganti Gambar")
                }

                Spacer(modifier = Modifier.height(12.dp))

                imageUri?.let {

                    AsyncImage(
                        model = it,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = {

                        viewModel.update(
                            Event(
                                id = id,
                                title = title,
                                description = description,
                                date = date,
                                location = location,
                                imageUri =
                                    imageUri?.toString() ?: ""
                            )
                        )

                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("✅ Update Event")
                }
            }
        }
    }
}