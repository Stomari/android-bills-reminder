package com.example.billsreminder.data

import com.example.billsreminder.data.room.ServicesDao
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun getAllServices(): Flow<List<Service>>

    suspend fun insertService(service: Service)
}

class DefaultDatabaseRepository(private val servicesDao: ServicesDao) : DatabaseRepository {
    override fun getAllServices(): Flow<List<Service>> = servicesDao.getAllServices()

    override suspend fun insertService(service: Service) = servicesDao.insertService(service)
}