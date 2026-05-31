package david.arias.addressmanager.infraestructure.repositories

import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.domain.repositories.AddressRepository
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import david.arias.addressmanager.infraestructure.mappers.AddressMapper
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val dao: AddressDao
) : AddressRepository {

    override suspend fun getAddresses(): List<Address> {
        return dao.getAll().map { AddressMapper.toDomain(it) }
    }

    override suspend fun updateAddress(address: Address) {
        val updated = address.copy(
            modifiedDate = System.currentTimeMillis()
        )
        dao.update(AddressMapper.toEntity(updated))
    }

    override suspend fun getCities(): List<String> {
        return dao.getCities()
    }

    override suspend fun getStates(): List<String> {
        return dao.getStates()
    }
}