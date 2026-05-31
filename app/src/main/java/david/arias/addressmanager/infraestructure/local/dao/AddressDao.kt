package david.arias.addressmanager.infraestructure.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import david.arias.addressmanager.infraestructure.local.entities.AddressEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddressDao {

    @Query("SELECT * FROM address WHERE id = :id LIMIT 1")
    fun getById(id: Long): Flow<AddressEntity>

    @Query("SELECT * FROM address")
    fun getAll(): Flow<List<AddressEntity>>

    @Query("SELECT DISTINCT city FROM address")
    suspend fun getCities(): List<String>

    @Query("SELECT DISTINCT stateProvince FROM address")
    suspend fun getStates(): List<String>

    @Update
    suspend fun update(address: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(addresses: List<AddressEntity>)
}
