package david.arias.addressmanager.infraestructure.mappers


import david.arias.addressmanager.config.helpers.DateHelper
import david.arias.addressmanager.infraestructure.local.entities.AddressEntity

object AddressCsvMapper {

    fun fromCsvLine(line: String): AddressEntity {
        val parts = line.split(",")

        return AddressEntity(
            id = parts[0].toInt(),
            addressLine1 = parts[1],
            addressLine2 = parts[2].takeIf { it.isNotBlank() && it != "NULL" },
            city = clean(parts[3]),
            stateProvince = clean(parts[4]),
            countryRegion = parts[5],
            postalCode = parts[6],
            rowguid = parts[7],
            modifiedDate = DateHelper.toEpochMillis(parts[8]),
        )
    }

    private fun clean(value: String): String {
        return value
            .trim()
            .removePrefix("\"")
            .removeSuffix("\"")
    }
}