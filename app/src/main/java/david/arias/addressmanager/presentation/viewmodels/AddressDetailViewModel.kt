package david.arias.addressmanager.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.domain.repositories.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddressDetailState(
    val isLoading: Boolean = false,
    val address: Address? = null,
    val cities: List<String> = emptyList(),
    val states: List<String> = emptyList(),
    val error: String? = null,
    val isSaved: Boolean = false
)

@HiltViewModel
class AddressDetailViewModel @Inject constructor(
    private val repository: AddressRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val addressId: Long =
        savedStateHandle.get<String>("addressId")?.toLongOrNull() ?: 0L

    private val _uiState = MutableStateFlow(AddressDetailState())
    val uiState = _uiState.asStateFlow()

    init {
        observeAddress()
        loadCities()
        loadStates()
    }

    private fun observeAddress() {
        repository.getById(addressId)
            .onEach { address ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        address = address
                    )
                }
            }
            .launchIn(viewModelScope)
    }

    private fun loadCities() {
        viewModelScope.launch {
            runCatching {
                repository.getCities()
            }.onSuccess { cities ->
                _uiState.update { it.copy(cities = cities) }
            }
        }
    }

    private fun loadStates() {
        viewModelScope.launch {
            runCatching {
                repository.getStates()
            }.onSuccess { states ->
                _uiState.update { it.copy(states = states) }
            }
        }
    }

    fun onCityChange(city: String) {
        _uiState.update { state ->
            state.address?.let {
                state.copy(address = it.copy(city = city))
            } ?: state
        }
    }

    fun onStateChange(stateValue: String) {
        _uiState.update { state ->
            state.address?.let {
                state.copy(address = it.copy(stateProvince = stateValue))
            } ?: state
        }
    }

    fun save() {
        viewModelScope.launch {

            val current = _uiState.value.address ?: return@launch

            runCatching {

                repository.updateAddress(
                    current.copy(
                        modifiedDate = System.currentTimeMillis()
                    )
                )

            }.onSuccess {

                _uiState.update {
                    it.copy(isSaved = true)
                }

            }.onFailure { error ->
                _uiState.update {
                    it.copy(error = error.message)
                }
            }
        }
    }
    fun onSaveHandled() {
        _uiState.update {
            it.copy(isSaved = false)
        }
    }

}