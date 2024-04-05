package com.example.billsreminder.ui.screens.form

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.billsreminder.BillsReminderApplication
import com.example.billsreminder.data.DatabaseRepository
import com.example.billsreminder.data.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

data class ServiceFormUiState(
    val name: String = "",
    val description: String = "",
    val price: String = ""
)

class ServiceFormScreenViewModel(
    savedStateHandle: SavedStateHandle,
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    // Service Id retrieved if editing
    private val serviceId: Int? = savedStateHandle["serviceId"]

    init {
        if (serviceId !== null) {
            viewModelScope.launch {
                val service = databaseRepository.getService(serviceId).first()
                updateServiceFormState(
                    with(service) {
                        ServiceFormUiState(
                            name = name,
                            description = description ?: "",
                            price = if (price !== null) price.toString() else "",
                        )
                    }
                )
            }
        }
    }

    private var _uiState = MutableStateFlow(ServiceFormUiState())
    val uiState: StateFlow<ServiceFormUiState> = _uiState.asStateFlow()

    fun updateServiceFormState(formState: ServiceFormUiState) {
        _uiState.value = formState
    }

    suspend fun saveService() {
        val service = with(_uiState.value) {
            Service(
                name = name,
                description = description.ifEmpty { null },
                price = if (price.isNotEmpty() && price.toDouble() > 0.0) price.toDouble() else null
            )
        }
        if (serviceId !== null) {
            databaseRepository.updateService(service.copy(id = serviceId))
        } else {
        databaseRepository.insertService(service)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BillsReminderApplication)
                ServiceFormScreenViewModel(
                    this.createSavedStateHandle(),
                    application.container.databaseRepository
                )
            }
        }
    }
}