package com.aamagon.regucars.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideDataRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://mock.api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}