package david.arias.addressmanager.infraestructure.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import david.arias.addressmanager.config.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.ADDRESS_TABLE)
data class AddressEntity(
    @PrimaryKey val id: Int,
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val stateProvince: String,
    val countryRegion: String,
    val postalCode: String,
    val rowguid: String,
    val modifiedDate: Long,
)