package com.example.billsreminder

import android.app.Application
import com.example.billsreminder.data.AppContainer
import com.example.billsreminder.data.DefaultAppContainer

class BillsReminderApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}