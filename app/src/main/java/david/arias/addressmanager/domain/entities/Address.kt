package david.arias.addressmanager.domain.entities

data class Address(
    val id: Int,
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val stateProvince: String,
    val countryRegion: String,
    val postalCode: String,
    val rowguid: String,
    val modifiedDate: Long,
)