package david.arias.addressmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import david.arias.addressmanager.config.navigation.AppScreens
import david.arias.addressmanager.presentation.components.AddressFiltersSection
import david.arias.addressmanager.presentation.components.AddressItem
import david.arias.addressmanager.presentation.viewmodels.AddressListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressListScreen(
    navController: NavController,
    addressListViewModel: AddressListViewModel = hiltViewModel(),
) {

    val state by addressListViewModel.uiState.collectAsState()
    val filteredAddresses by addressListViewModel.filteredAddresses.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de direcciones") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {

            Column(
                modifier = Modifier.padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AddressFiltersSection(
                    cities = state.cities,
                    states = state.states,
                    selectedCity = state.selectedCity,
                    selectedState = state.selectedState,
                    onCitySelected = { addressListViewModel.onCitySelected(it) },
                    onStateSelected = { addressListViewModel.onStateSelected(it) },
                    onClear = { addressListViewModel.clearFilters() }
                )

                when {

                    state.isLoading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    state.error != null -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(state.error ?: "Error")
                        }
                    }

                    filteredAddresses.isEmpty() -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("No se encontraron direcciones")
                        }
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = PaddingValues(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(filteredAddresses.size) { index ->
                                val currentAddress =  filteredAddresses[index]
                                AddressItem(
                                    address = currentAddress,
                                    onEditClick = { navController.navigate("${AppScreens.AddressDetail.route}/${currentAddress.id}")}
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}