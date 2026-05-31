package david.arias.addressmanager.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import david.arias.addressmanager.domain.entities.Address
import david.arias.addressmanager.domain.repositories.AddressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

    private val _selectedCity = MutableStateFlow<String?>(null)
    private val _selectedState = MutableStateFlow<String?>(null)

    private val addressesFlow = repository.getAddresses()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val uiState = combine(
        addressesFlow,
        _selectedCity,
        _selectedState
    ) { data, selectedCity, selectedState ->

        AddressListState(
            isLoading = false,
            addresses = data,
            cities = data.map { it.city }
                .filter { it.isNotBlank() }
                .distinct()
                .sorted(),
            states = data.map { it.stateProvince }
                .filter { it.isNotBlank() }
                .distinct()
                .sorted(),
            selectedCity = selectedCity,
            selectedState = selectedState
        )

    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AddressListState(isLoading = true)
    )

    fun onCitySelected(city: String?) {
        _selectedCity.value = city
    }

    fun onStateSelected(state: String?) {
        _selectedState.value = state
    }

    fun clearFilters() {
        _selectedCity.value = null
        _selectedState.value = null
    }

    val filteredAddresses = combine(
        addressesFlow,
        _selectedCity,
        _selectedState
    ) { list, city, state ->

        list.filter { address ->

            val cityMatch = city?.let { it == address.city } ?: true
            val stateMatch = state?.let { it == address.stateProvince } ?: true

            cityMatch && stateMatch
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )
}