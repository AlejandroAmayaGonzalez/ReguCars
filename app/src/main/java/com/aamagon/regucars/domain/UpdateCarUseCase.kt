package com.aamagon.regucars.domain

import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.Car
import javax.inject.Inject

class UpdateCarUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(car: Car) = repository.updateCar(car)
}