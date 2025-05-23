package com.aamagon.regucars.core.di

import android.content.Context
import androidx.room.Room
import com.aamagon.regucars.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "ReguCarsDB"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, Database::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideCarDao(db: Database) = db.getCarsDao()

    @Singleton
    @Provides
    fun provideUserDao(db: Database) = db.getUserDao()
}