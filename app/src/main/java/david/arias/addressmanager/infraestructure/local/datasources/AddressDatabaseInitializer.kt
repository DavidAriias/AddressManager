package david.arias.addressmanager.infraestructure.local.datasources

import android.content.Context
import android.content.SharedPreferences
import david.arias.addressmanager.config.constants.PrefsConstants
import david.arias.addressmanager.infraestructure.local.dao.AddressDao
import androidx.core.content.edit

class AddressDatabaseInitializer(
    private val context: Context,
    private val dao: AddressDao,
    private val prefs: SharedPreferences
) {


    suspend fun initialize() {

        val alreadyInitialized = prefs.getBoolean(
            PrefsConstants.KEY_DB_INITIALIZED,
            false
        )

        if (alreadyInitialized) return

        val reader = AddressCsvReader(context)
        val data = reader.read()

        dao.insertAll(data)

        prefs.edit {
            putBoolean(
                PrefsConstants.KEY_DB_INITIALIZED,
                true
            )
        }
    }
}