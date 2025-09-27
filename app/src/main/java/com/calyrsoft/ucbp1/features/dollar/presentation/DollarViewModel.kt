package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.usecase.FetchDollarUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DollarViewModel(
    private val fetchDollarUseCase: FetchDollarUseCase
) : ViewModel() {

    sealed class DollarUIState {
        object Loading : DollarUIState()
        data class Error(val message: String) : DollarUIState()
        data class Success(val data: DollarModel) : DollarUIState()
    }

    private val _uiState = MutableStateFlow<DollarUIState>(DollarUIState.Loading)
    val uiState: StateFlow<DollarUIState> = _uiState.asStateFlow()

    init {
        getDollar()
    }

    fun getDollar() {
        viewModelScope.launch(Dispatchers.IO) {
            fetchDollarUseCase.invoke()
                .catch { e -> _uiState.value = DollarUIState.Error(e.message ?: "Error desconocido") }
                .collect { data ->
                    _uiState.value = DollarUIState.Success(data)
                }
        }
    }

    /**
     * Convierte el timestamp (Long) a un string legible
     * Ejemplo: "27/09/2025 14:30"
     */
    fun formatFecha(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }
}
