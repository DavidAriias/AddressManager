package david.arias.addressmanager.domain.repositories

import david.arias.addressmanager.domain.entities.Address
import kotlinx.coroutines.flow.Flow

interface AddressRepository {

    fun getById(id: Long) : Flow<Address>

    fun getAddresses(): Flow<List<Address>>

    suspend fun updateAddress(address: Address)

    suspend fun getCities(): List<String>

    suspend fun getStates(): List<String>
}