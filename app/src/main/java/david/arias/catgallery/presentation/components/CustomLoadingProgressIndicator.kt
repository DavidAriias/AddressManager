package david.arias.catgallery.presentation.components

import android.R.attr.padding
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomLoadingProgressIndicator() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}