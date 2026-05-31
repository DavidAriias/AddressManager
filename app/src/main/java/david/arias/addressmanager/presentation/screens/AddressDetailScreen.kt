package david.arias.addressmanager.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavController
import david.arias.addressmanager.config.helpers.DateHelper
import david.arias.addressmanager.presentation.components.CustomErrorMessage
import david.arias.addressmanager.presentation.components.CustomLoadingCircularProgressIndicator
import david.arias.addressmanager.presentation.components.EditAddressBottomSheet
import david.arias.addressmanager.presentation.viewmodels.AddressDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressDetailScreen(
    navController: NavController,
    viewModel: AddressDetailViewModel = hiltViewModel()
) {

    val state by viewModel.uiState.collectAsState()

    var showSheet by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSaved) {

        if (state.isSaved) {

            snackbarHostState.showSnackbar(
                message = "Dirección actualizada correctamente"
            )

            viewModel.onSaveHandled()
        }
    }

    Scaffold(
        snackbarHost =  { SnackbarHost(snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("Detalle dirección") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { padding ->

        when {

            state.isLoading -> {
                CustomLoadingCircularProgressIndicator()
            }

            state.error != null -> {
                CustomErrorMessage(state.error)
            }

            state.address != null -> {

                val address = state.address

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    Text(
                        text = address?.addressLine1 ?: "Desconocido",
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text("Ciudad: ${address?.city}")
                    Text("Estado: ${address?.stateProvince}")
                    Text("Postal: ${address?.postalCode}")

                    Text(
                        text = "Última modificación: ${
                            DateHelper.formatEpochToDate(address?.modifiedDate ?: 0)
                        }"
                    )

                    Button(
                        onClick = { showSheet = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Editar dirección")
                    }
                }
            }
        }

        state.address?.let { address ->

            if (showSheet) {
                EditAddressBottomSheet(
                    address = address,
                    cities = state.cities,
                    states = state.states,
                    onDismiss = { showSheet = false },
                    onSave = { city, stateValue ->

                        viewModel.onCityChange(city)
                        viewModel.onStateChange(stateValue)
                        viewModel.save()

                        showSheet = false
                    }
                )
            }
        }
    }
}