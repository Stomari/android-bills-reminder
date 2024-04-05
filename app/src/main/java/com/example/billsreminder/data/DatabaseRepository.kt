package com.example.billsreminder.data

import com.example.billsreminder.data.room.ServicesDao
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    fun getAllServices(): Flow<List<Service>>

    fun getService(serviceId: Int): Flow<Service>

    suspend fun insertService(service: Service)

    suspend fun deleteService(service: Service)

    suspend fun updateService(service: Service)
}

class DefaultDatabaseRepository(private val servicesDao: ServicesDao) : DatabaseRepository {
    override fun getAllServices(): Flow<List<Service>> = servicesDao.getAllServices()

    override fun getService(serviceId: Int): Flow<Service> = servicesDao.getService(serviceId)

    override suspend fun insertService(service: Service) = servicesDao.insertService(service)

    override suspend fun deleteService(service: Service) = servicesDao.deleteService(service)

    override suspend fun updateService(service: Service) = servicesDao.updateService(service)
}