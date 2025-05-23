package com.aamagon.regucars.domain

import com.aamagon.regucars.core.extensions.toEntity
import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.Car
import javax.inject.Inject

class GetCarsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Car> {
        val response = repository.getCarsFromApi()

        return if (response.isNotEmpty()){
            // Clear DB
            repository.clearCars()

            // Insert the returned content
            repository.insertCars(response.map { it.toEntity(id = response.indexOf(it)) })

            response
        }else{
            repository.getCarsFromDatabase()
        }
    }
}