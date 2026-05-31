package david.arias.addressmanager.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import david.arias.addressmanager.config.helpers.DateHelper
import david.arias.addressmanager.domain.entities.Address

@Composable
fun AddressItem(
    address: Address,
    onEditClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            Text(
                text = address.addressLine1,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${address.city}, ${address.stateProvince}",
                style = MaterialTheme.typography.bodyMedium
            )

            Text(
                text = "Última modificación: ${DateHelper.formatEpochToDate(address.modifiedDate)}",
                style = MaterialTheme.typography.bodySmall
            )

            Button(
                onClick = onEditClick
            ) {
                Text("Editar")
            }
        }
    }
}