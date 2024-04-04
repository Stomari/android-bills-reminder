package com.example.billsreminder.data

import android.content.Context
import com.example.billsreminder.data.room.BillsReminderDatabase

interface AppContainer {
    val databaseRepository: DatabaseRepository
}

class DefaultAppContainer(private val context: Context) : AppContainer {
    override val databaseRepository: DatabaseRepository by lazy {
        DefaultDatabaseRepository(BillsReminderDatabase.getDatabase(context).servicesDao())
    }
}