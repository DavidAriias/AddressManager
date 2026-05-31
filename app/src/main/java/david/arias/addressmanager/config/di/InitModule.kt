package david.arias.addressmanager.config.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import david.arias.addressmanager.config.constants.PrefsConstants
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import david.arias.addressmanager.infraestructure.local.datasources.AddressDatabaseInitializer
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object InitModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
       @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences(
            PrefsConstants.PREFS_NAME,
            Context.MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideInitializer(
        @ApplicationContext context: Context,
        dao: AddressDao,
        prefs: SharedPreferences
    ): AddressDatabaseInitializer {
        return AddressDatabaseInitializer(context, dao, prefs)
    }
}