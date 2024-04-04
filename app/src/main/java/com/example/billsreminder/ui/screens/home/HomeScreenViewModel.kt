package com.example.billsreminder.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.billsreminder.BillsReminderApplication
import com.example.billsreminder.data.DatabaseRepository

class HomeScreenViewModel(
    private val databaseRepository: DatabaseRepository
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BillsReminderApplication)
                HomeScreenViewModel(application.container.databaseRepository)
            }
        }
    }
}