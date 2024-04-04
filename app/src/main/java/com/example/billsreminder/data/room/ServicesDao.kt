package com.example.billsreminder.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.billsreminder.data.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicesDao {
    @Query("SELECT * from services")
    fun getAllServices(): Flow<List<Service>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)
}