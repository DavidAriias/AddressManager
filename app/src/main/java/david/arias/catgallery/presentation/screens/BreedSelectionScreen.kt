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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import coil.compose.AsyncImage
import david.arias.catgallery.domain.entities.Breed
import david.arias.catgallery.presentation.components.CustomLoadingProgressIndicator
import david.arias.catgallery.presentation.components.CustomMessageError
import david.arias.catgallery.presentation.viewmodels.BreedSelectionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BreedSelectionScreen(
    navController: NavController,
    breedSelectionViewModel: BreedSelectionViewModel = hiltViewModel(),
) {

    val uiState by
    breedSelectionViewModel.uiState.collectAsState()

    var expanded by remember {
        mutableStateOf(false)
    }

    var selectedBreed by remember {
        mutableStateOf<Breed?>(null)
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

        when {
            uiState.isLoading -> {
                CustomLoadingProgressIndicator()
            }

            uiState.error != null -> {
                CustomMessageError(uiState.error ?: "Ocurrió un error")
            }

            else -> {
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

                    uiState.error?.let { error ->

                        Text(
                            text = error,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        }
                    ) {

                        OutlinedTextField(
                            value = selectedBreed?.name ?: "",
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

                            uiState.breeds.forEach { breed ->

                                DropdownMenuItem(
                                    text = {
                                        Text(breed.name)
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
                        onValueChange = { value ->
                            limit = value.filter { it.isDigit() }
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
                        onClick = {

                            val breedId = selectedBreed?.id ?: return@Button
                            val imageLimit = limit.toIntOrNull() ?: return@Button

                            // TODO:
                            // Navegar a ResultsScreen
                            // navController.navigate(...)

                        },
                        enabled = limit.toIntOrNull() != null,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Search Cats")
                    }
                }
            }
        }
    }
}