package com.aamagon.regucars.data

import com.aamagon.regucars.core.extensions.toDomain
import com.aamagon.regucars.data.network.ApiService
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.domain.model.User
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getCarsFromApi(): List<Car>{
        return apiService.getCars().map { it.toDomain() }
    }

    suspend fun getUsersFromApi(): List<User>{
        return apiService.getUsers().map { it.toDomain() }
    }
}