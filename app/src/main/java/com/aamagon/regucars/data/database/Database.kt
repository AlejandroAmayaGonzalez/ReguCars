package com.aamagon.regucars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.aamagon.regucars.core.extensions.Converters
import com.aamagon.regucars.data.database.dao.CarDao
import com.aamagon.regucars.data.database.dao.UserDao
import com.aamagon.regucars.data.database.entities.CarEntity
import com.aamagon.regucars.data.database.entities.UserEntity

@Database(entities = [CarEntity::class, UserEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class Database: RoomDatabase() {
    abstract fun getCarsDao(): CarDao
    abstract fun getUserDao(): UserDao
}