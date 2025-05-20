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

    @Cars
    @Singleton
    @Provides
    fun provideCarsRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://d869b751-7eb6-44b9-8db5-33f51b4f1d7a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Users
    @Singleton
    @Provides
    fun provideUsersRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://d869b751-7eb6-44b9-8db5-33f51b4f1d7a.mock.pstmn.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}