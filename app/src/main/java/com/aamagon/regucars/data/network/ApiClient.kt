package com.aamagon.regucars.data.network

import co.infinum.retromock.meta.Mock
import co.infinum.retromock.meta.MockResponse
import com.aamagon.regucars.data.model.DataModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @Mock
    @MockResponse(body = "data.json")
    @GET(".")
    suspend fun getData(): Response<DataModel>
}