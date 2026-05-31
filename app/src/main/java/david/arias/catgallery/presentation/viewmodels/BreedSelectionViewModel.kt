package david.arias.catgallery.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import david.arias.catgallery.domain.entities.Breed
import david.arias.catgallery.domain.repositories.CatRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BreedSelectionUiState(
    val isLoading: Boolean = false,
    val breeds: List<Breed> = emptyList(),
    val error: String? = null
)

@HiltViewModel
class BreedSelectionViewModel @Inject constructor(
    private val repository: CatRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        BreedSelectionUiState()
    )

    val uiState = _uiState.asStateFlow()

    init {
        loadBreeds()
    }

    private fun loadBreeds() {
        viewModelScope.launch {
            setLoading()

            runCatching {
                repository.getBreeds()
            }.onSuccess { breeds ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        breeds = breeds
                    )
                }
            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message ?: "Unexpected error"
                    )
                }
            }
        }
    }

    private fun setLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                error = null
            )
        }
    }
}