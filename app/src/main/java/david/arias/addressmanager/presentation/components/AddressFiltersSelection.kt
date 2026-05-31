package david.arias.addressmanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddressFiltersSection(
    cities: List<String>,
    states: List<String>,
    selectedCity: String?,
    selectedState: String?,
    onCitySelected: (String?) -> Unit,
    onStateSelected: (String?) -> Unit,
    onClear: () -> Unit
) {
    var cityExpanded by remember { mutableStateOf(false) }
    var stateExpanded by remember { mutableStateOf(false) }

    Column(
        verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.padding(12.dp)
    ) {

        // CITY
        ExposedDropdownMenuBox(
            expanded = cityExpanded,
            onExpandedChange = { cityExpanded = !cityExpanded }
        ) {
            OutlinedTextField(
                value = selectedCity ?: "Todas las ciudades",
                onValueChange = {},
                readOnly = true,
                label = { Text("Ciudad") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = cityExpanded,
                onDismissRequest = { cityExpanded = false }
            ) {
                cities.forEach { city ->
                    DropdownMenuItem(
                        text = { Text(city) },
                        onClick = {
                            onCitySelected(city)
                            cityExpanded = false
                        }
                    )
                }
            }
        }

        // STATE
        ExposedDropdownMenuBox(
            expanded = stateExpanded,
            onExpandedChange = { stateExpanded = !stateExpanded }
        ) {
            OutlinedTextField(
                value = selectedState ?: "Todos los estados",
                onValueChange = {},
                readOnly = true,
                label = { Text("Estado / Provincia") },
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = stateExpanded,
                onDismissRequest = { stateExpanded = false }
            ) {
                states.forEach { st ->
                    DropdownMenuItem(
                        text = { Text(st) },
                        onClick = {
                            onStateSelected(st)
                            stateExpanded = false
                        }
                    )
                }
            }
        }

        TextButton(onClick = onClear) {
            Text("Limpiar filtros")
        }
    }
}