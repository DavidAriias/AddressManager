package david.arias.addressmanager.infraestructure.mappers

import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.infraestructure.local.entities.AddressEntity

object AddressMapper {

    fun toDomain(entity: AddressEntity): Address {
        return Address(
            id = entity.id,
            addressLine1 = entity.addressLine1,
            addressLine2 = entity.addressLine2,
            city = entity.city,
            stateProvince = entity.stateProvince,
            countryRegion = entity.countryRegion,
            postalCode = entity.postalCode,
            rowguid = entity.rowguid,
            modifiedDate = entity.modifiedDate
        )
    }

    fun toDomainList(entities: List<AddressEntity>): List<Address> {
        return entities.map { toDomain(it) }
    }

    fun toEntity(domain: Address): AddressEntity {
        return AddressEntity(
            id = domain.id,
            addressLine1 = domain.addressLine1,
            addressLine2 = domain.addressLine2,
            city = domain.city,
            stateProvince = domain.stateProvince,
            countryRegion = domain.countryRegion,
            postalCode = domain.postalCode,
            rowguid = domain.rowguid,
            modifiedDate = domain.modifiedDate
        )
    }

    fun toEntityList(domainList: List<Address>): List<AddressEntity> {
        return domainList.map { toEntity(it) }
    }
}