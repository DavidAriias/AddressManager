package david.arias.addressmanager.infraestructure.repositories

import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.domain.repositories.AddressRepository
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import david.arias.addressmanager.infraestructure.mappers.AddressMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AddressRepositoryImpl @Inject constructor(
    private val dao: AddressDao
) : AddressRepository {

    override fun getById(id: Long): Flow<Address> {
        return dao.getById(id)
            .map { entity ->
                AddressMapper.toDomain(entity)
            }
    }

    override fun getAddresses(): Flow<List<Address>> {
        return dao.getAll().map { list ->
            list.map { AddressMapper.toDomain(it) }
        }
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