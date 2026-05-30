package david.arias.catgallery.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSelectionScreen(navController: NavController) {

    var expanded by remember {
        mutableStateOf(false)
    }

    val breeds = listOf(
        "Bengal",
        "Persian",
        "Siamese",
        "Maine Coon"
    )

    var selectedBreed by remember {
        mutableStateOf("")
    }

    var limit by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Cat Explorer")
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Text(
                text = "Search Cats By Breed",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {

                OutlinedTextField(
                    value = selectedBreed,
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text("Breed")
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {

                    breeds.forEach { breed ->

                        DropdownMenuItem(
                            text = {
                                Text(breed)
                            },
                            onClick = {
                                selectedBreed = breed
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = limit,
                onValueChange = {
                    limit = it
                },
                label = {
                    Text("Limit")
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Cats")
            }
        }
    }
}