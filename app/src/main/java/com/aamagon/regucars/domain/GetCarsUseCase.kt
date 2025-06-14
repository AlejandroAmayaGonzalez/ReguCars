package com.aamagon.regucars.domain

import com.aamagon.regucars.core.extensions.toEntity
import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.Car
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(
    private val repository: Repository
) {
    // First return the data stored in DB. In other case return from API
    suspend operator fun invoke(): List<Car> {
        val dbResponse = repository.getCarsFromDatabase()
        val apiResponse = repository.getCarsFromApi()

        return if (dbResponse.isNotEmpty() && dbResponse.size == apiResponse.size){
            dbResponse
        }else{
            // Clear DB
            repository.clearCars()

            // Insert
            repository.insertCars(apiResponse.map { it.toEntity(it.id) })

            apiResponse
        }
    }
}