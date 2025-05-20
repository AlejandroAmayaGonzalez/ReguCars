package com.aamagon.regucars.core.di

import android.content.Context
import co.infinum.retromock.Retromock
import com.aamagon.regucars.core.bodyFactory.AssetsBodyFactory
import com.aamagon.regucars.data.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetromockModule {

    @Singleton
    @Provides
    fun provideCarsRetromock(
        @ApplicationContext context: Context,
        retrofit: Retrofit
    ): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .defaultBodyFactory(AssetsBodyFactory(context.assets::open))
            .build()
    }

    @Singleton
    @Provides
    fun provideApiAclient(retromock: Retromock): ApiClient{
        return retromock.create(ApiClient::class.java)
    }
}