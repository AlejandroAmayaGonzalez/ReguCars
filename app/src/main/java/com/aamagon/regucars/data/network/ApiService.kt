package com.aamagon.regucars.data.network

import com.aamagon.regucars.data.model.CarModel
import com.aamagon.regucars.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiService @Inject constructor(
    private val api: ApiClient
) {

    suspend fun getCars(): List<CarModel>{
        return withContext(Dispatchers.IO) {
            val response = api.getCars()
            response.body() ?: emptyList()
        }
    }

    suspend fun getUsers(): List<UserModel>{
        return withContext(Dispatchers.IO) {
            val response = api.getUsers()
            response.body() ?: emptyList()
        }
    }
}