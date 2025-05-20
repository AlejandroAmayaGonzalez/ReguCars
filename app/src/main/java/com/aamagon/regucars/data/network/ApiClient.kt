package com.aamagon.regucars.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.aamagon.regucars.data.model.CarModel
import com.aamagon.regucars.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @Mock
    @GET(".")
    @MockResponse(body = "cars.json")
    suspend fun getCars(): Response<List<CarModel>>

    @Mock
    @GET(".")
    @MockResponse(body = "users.json")
    suspend fun getUsers(): Response<List<UserModel>>
}