package david.arias.addressmanager.domain.repositories

import david.arias.addressmanager.domain.entities.Address

interface AddressRepository {

    suspend fun getAddresses(): List<Address>

    suspend fun updateAddress(address: Address)

    suspend fun getCities(): List<String>

    suspend fun getStates(): List<String>
}