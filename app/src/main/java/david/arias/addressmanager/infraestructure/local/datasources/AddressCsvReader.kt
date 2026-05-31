package david.arias.addressmanager.infraestructure.local.datasources

import android.content.Context
import david.arias.addressmanager.config.constants.FileConstants
import david.arias.addressmanager.infraestructure.local.entities.AddressEntity
import david.arias.addressmanager.infraestructure.mappers.AddressCsvMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddressCsvReader(
    private val context: Context
) {

    suspend fun read(): List<AddressEntity> = withContext(Dispatchers.IO) {

        context.assets.open(FileConstants.ADDRESS_CSV)
            .bufferedReader()
            .useLines { lines ->
                lines
                    .drop(1)
                    .filter { it.isNotBlank() }
                    .map { AddressCsvMapper.fromCsvLine(it) }
                    .toList()
            }
    }
}