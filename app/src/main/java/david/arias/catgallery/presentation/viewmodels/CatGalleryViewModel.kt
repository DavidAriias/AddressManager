package david.arias.catgallery.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import david.arias.catgallery.domain.entities.CatImage
import david.arias.catgallery.domain.repositories.CatRepository
import david.arias.catgallery.domain.repositories.PrinterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class CatGalleryState(
    val isLoading: Boolean = false,
    val isPrinting: Boolean = false,
    val cats: List<CatImage> = emptyList(),
    val breedName: String = "",
    val error: String? = null,
    val snackbarMessage: String? = null
)

@HiltViewModel
class CatGalleryViewModel @Inject constructor(
    private val catRepository: CatRepository,
    private val printerRepository: PrinterRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val breedId: String =
        checkNotNull(savedStateHandle["breedId"])

    private val breedName: String =
        checkNotNull(savedStateHandle["breedName"])
    private val limit: Int =
        checkNotNull(savedStateHandle["limit"])

    private val _uiState =
        MutableStateFlow(CatGalleryState(breedName = breedName))

    val uiState = _uiState.asStateFlow()

    init {
        loadCats()
    }

    fun printCat(cat: CatImage) {
        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isPrinting = true
                )
            }

            printerRepository
                .printCat(cat)
                .onSuccess {

                    _uiState.update {
                        it.copy(
                            isPrinting = false,
                            snackbarMessage = "Imagen enviada a impresión"
                        )
                    }
                }
                .onFailure { exception ->

                    _uiState.update {
                        it.copy(
                            isPrinting = false,
                            snackbarMessage = "Ocurrió un error al imprimir : ${exception.message}"
                        )
                    }
                }
        }
    }

    fun clearSnackbarMessage() {
        _uiState.update {
            it.copy(
                snackbarMessage = null
            )
        }
    }

    private fun loadCats() {

        viewModelScope.launch {

            _uiState.update {
                it.copy(
                    isLoading = true,
                    error = null
                )
            }

            runCatching {

                catRepository.getImagesByBreed(
                    breedId = breedId,
                    limit = limit
                )

            }.onSuccess { cats ->

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        cats = cats
                    )
                }

            }.onFailure { exception ->

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = exception.message
                    )
                }

            }
        }
    }
}