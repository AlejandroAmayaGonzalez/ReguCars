package com.aamagon.regucars.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.aamagon.regucars.data.model.CarModel
import com.aamagon.regucars.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @Mock
    @MockResponse(body = "data.json")
    @GET("/coches")
    suspend fun getCars(): Response<List<CarModel>>

    @Mock
    @MockResponse(body = "data.json")
    @GET("/usuarios")
    suspend fun getUsers(): Response<List<UserModel>>
}