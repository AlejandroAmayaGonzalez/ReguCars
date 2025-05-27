package com.aamagon.regucars.data

import com.aamagon.regucars.core.extensions.toDomain
import com.aamagon.regucars.core.extensions.toEntity
import com.aamagon.regucars.data.database.dao.CarDao
import com.aamagon.regucars.data.database.dao.UserDao
import com.aamagon.regucars.data.database.entities.CarEntity
import com.aamagon.regucars.data.database.entities.UserEntity
import com.aamagon.regucars.data.network.ApiService
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.domain.model.User
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val carDao: CarDao,
    private val userDao: UserDao
) {

    // Api functions
    suspend fun getCarsFromApi(): List<Car>{
        return apiService.getCars().map { it.toDomain() }
    }

    suspend fun getUsersFromApi(): List<User>{
        return apiService.getUsers().map { it.toDomain() }
    }

    // Return data from database
    suspend fun getCarsFromDatabase(): List<Car> {
        val response = carDao.getAllCars()

        // Map the entities to domain model and return it
        return response.map { it.toDomain() }
    }

    suspend fun getUsersFromDatabase(): List<User> {
        return userDao.getAllUsers().map { it.toDomain() }
    }

    // Return the cars with the favourite field true
    suspend fun getFavCars(): List<Car> {
        return carDao.getFavCars().map { it.toDomain() }
    }

    // Update cars to mark it as favourite
    suspend fun updateCar(id: Int, car: Car) = carDao.updateCar(car.toEntity(id))

    // Insert in tables
    suspend fun insertCars(cars: List<CarEntity>) = carDao.insertAllCars(cars)
    suspend fun insertUsers(users: List<UserEntity>) = userDao.insertAllUsers(users)

    // Delete tables
    suspend fun clearCars() = carDao.deleteCars()
    suspend fun clearUsers() = userDao.deleteUsers()
}