package com.example.billsreminder.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.billsreminder.BillsReminderApplication
import com.example.billsreminder.data.DatabaseRepository
import com.example.billsreminder.data.Service
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class HomeScreenViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    val servicesList: StateFlow<List<Service>> =
        databaseRepository.getAllServices()
            .stateIn(
                scope = viewModelScope,
                initialValue = listOf(),
                started = SharingStarted.WhileSubscribed(5_000L)
            )

    suspend fun deleteService(service: Service) {
        databaseRepository.deleteService(service)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BillsReminderApplication)
                HomeScreenViewModel(application.container.databaseRepository)
            }
        }
    }
}