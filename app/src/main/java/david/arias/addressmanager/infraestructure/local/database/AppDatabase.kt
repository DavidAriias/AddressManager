package david.arias.addressmanager.infraestructure.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import david.arias.addressmanager.config.constants.DatabaseConstants
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import david.arias.addressmanager.infraestructure.local.entities.AddressEntity

@Database(
    entities = [AddressEntity::class],
    version = DatabaseConstants.VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun addressDao(): AddressDao
}