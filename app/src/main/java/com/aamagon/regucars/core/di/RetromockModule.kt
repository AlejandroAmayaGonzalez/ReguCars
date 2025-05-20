package com.aamagon.regucars.core.di

import android.content.Context
import co.infinum.retromock.Retromock
import com.aamagon.regucars.core.bodyFactory.AssetsBodyFactory
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

    @Cars
    @Singleton
    @Provides
    fun provideCarsRetromock(
        @ApplicationContext context: Context,
        @Cars retrofit: Retrofit
    ): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .defaultBodyFactory(AssetsBodyFactory(context.assets::open))
            .build()
    }

    @Users
    @Singleton
    @Provides
    fun provideUsersRetromock(
        @ApplicationContext context: Context,
        @Users retrofit: Retrofit
    ): Retromock{
        return Retromock.Builder()
            .retrofit(retrofit)
            .defaultBodyFactory(AssetsBodyFactory(context.assets::open))
            .build()
    }
}