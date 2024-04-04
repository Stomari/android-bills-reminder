package com.example.billsreminder.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.billsreminder.data.Service

@Database(
    entities = [Service::class],
    version = 1,
    exportSchema = false
)
abstract class BillsReminderDatabase : RoomDatabase() {
    abstract fun servicesDao(): ServicesDao

    companion object {
        @Volatile
        private var Instance: BillsReminderDatabase? = null

        fun getDatabase(context: Context): BillsReminderDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, BillsReminderDatabase::class.java, "bills_reminder_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}