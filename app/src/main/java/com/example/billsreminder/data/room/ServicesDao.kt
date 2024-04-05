package com.example.billsreminder.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.billsreminder.data.Service
import kotlinx.coroutines.flow.Flow

@Dao
interface ServicesDao {
    @Query("SELECT * from services")
    fun getAllServices(): Flow<List<Service>>

    @Query("SELECT * from services WHERE id = :serviceId")
    fun getService(serviceId: Int): Flow<Service>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: Service)

    @Delete
    suspend fun deleteService(service: Service)

    @Update
    suspend fun updateService(service: Service)
}