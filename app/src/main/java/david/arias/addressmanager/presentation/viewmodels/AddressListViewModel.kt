package david.arias.addressmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.domain.repositories.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AddressListState(
    val isLoading: Boolean = false,
    val addresses: List<Address> = emptyList(),
    val cities: List<String> = emptyList(),
    val states: List<String> = emptyList(),
    val selectedCity: String? = null,
    val selectedState: String? = null,
    val error: String? = null
)
@HiltViewModel
class AddressListViewModel @Inject constructor(
    private val repository: AddressRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddressListState())
    val uiState = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch {

            _uiState.update { it.copy(isLoading = true, error = null) }

            runCatching {
                repository.getAddresses()
            }.onSuccess { data ->
                Log.d("VM", "$data")
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        addresses = data,
                        cities = data.map { it.city }.distinct().sorted(),
                        states = data.map { it.stateProvince }.distinct().sorted()
                    )
                }

            }.onFailure { error ->
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = error.message
                    )
                }
            }
        }
    }

    fun onCitySelected(city: String?) {
        _uiState.update {
            it.copy(selectedCity = city)
        }
    }

    fun onStateSelected(state: String?) {
        _uiState.update {
            it.copy(selectedState = state)
        }
    }

    fun clearFilters() {
        _uiState.update {
            it.copy(
                selectedCity = null,
                selectedState = null
            )
        }
    }

    val filteredAddresses = uiState.map { state ->

        state.addresses.filter { address ->

            val cityMatch =
                state.selectedCity?.let { it == address.city } ?: true

            val stateMatch =
                state.selectedState?.let { it == address.stateProvince } ?: true

            cityMatch && stateMatch
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}