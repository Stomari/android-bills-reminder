package com.example.billsreminder.ui.screens.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.billsreminder.BillsReminderApplication
import com.example.billsreminder.data.DatabaseRepository
import com.example.billsreminder.data.Service
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ServiceFormUiState(
    val name: String = "",
    val description: String = "",
    val price: String = ""
)

class ServiceFormScreenViewModel(private val databaseRepository: DatabaseRepository) : ViewModel() {

    private var _uiState = MutableStateFlow(ServiceFormUiState())
    val uiState: StateFlow<ServiceFormUiState> = _uiState.asStateFlow()

    fun updateServiceFormState(formState: ServiceFormUiState) {
        _uiState.value = formState
    }

    suspend fun addService() {
        databaseRepository.insertService(
            with(_uiState.value) {
                Service(
                    name = name,
                    description = description.ifEmpty { null },
                    price = if (price.isNotEmpty() && price.toDouble() > 0.0) price.toDouble() else null
                )
            }
        )
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BillsReminderApplication)
                ServiceFormScreenViewModel(application.container.databaseRepository)
            }
        }
    }
}