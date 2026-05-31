package david.arias.addressmanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import david.arias.addressmanager.infraestructure.local.datasources.AddressDatabaseInitializer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class AddressManagerApp : Application() {

    @Inject
    lateinit var initializer: AddressDatabaseInitializer

    override fun onCreate() {
        super.onCreate()

        CoroutineScope(Dispatchers.IO).launch {
            initializer.initialize()
        }
    }
}