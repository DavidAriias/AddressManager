package david.arias.catgallery.infraestructure.repositories

import android.content.Context
import android.device.PrinterManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import androidx.core.graphics.scale
import coil.ImageLoader
import coil.request.ImageRequest
import dagger.hilt.android.qualifiers.ApplicationContext
import david.arias.catgallery.domain.entities.CatImage
import david.arias.catgallery.domain.repositories.PrinterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UrovoPrinterImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : PrinterRepository {

    companion object {
        private const val TAG = "UrovoPrinter"
    }
    private val printerManager by lazy {
        PrinterManager().apply {
            open()
        }
    }

    override suspend fun printCat(
        cat: CatImage
    ): Result<Unit> = withContext(Dispatchers.IO) {

        runCatching {

            Log.d(TAG, "Starting print process")
            Log.d(TAG, "Image URL: ${cat.url}")

            val status = printerManager.status

            Log.d(TAG, "Printer status $status")

            check(status == 0) {
                "Printer unavailable. Status: $status"
            }

            Log.d(TAG, "Downloading image")

            val result = ImageLoader(context).execute(
                ImageRequest.Builder(context)
                    .data(cat.url)
                    .allowHardware(false)
                    .build()
            )

            val bitmap = (result.drawable as? BitmapDrawable)
                ?.bitmap
                ?: error("Unable to load image")

            val scaledBitmap = bitmap.scale(
                width = 384,
                height = bitmap.height * 384 / bitmap.width
            )

            printerManager.setupPage(384, -1)

            printerManager.drawBitmap(
                scaledBitmap,
                0,
                0
            )

            val printResult = printerManager.printPage(0)

            Log.d(TAG, "Print result: $printResult")

            check(printResult == 0) {
                "Print failed. Code: $printResult"
            }

            printerManager.paperFeed(16)
        }.onFailure { exception ->

            Log.e(
                TAG,
                "Printing failed",
                exception
            )
        }
    }
}