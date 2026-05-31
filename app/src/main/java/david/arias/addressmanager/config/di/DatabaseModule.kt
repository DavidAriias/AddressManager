package david.arias.addressmanager.config.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import david.arias.addressmanager.config.constants.DatabaseConstants
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import david.arias.addressmanager.infraestructure.local.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DatabaseConstants.DB_NAME,
            )
            .build()
    }

    @Provides
    fun provideAddressDao(db: AppDatabase): AddressDao {
        return db.addressDao()
    }
}