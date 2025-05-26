package com.aamagon.regucars.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aamagon.regucars.data.database.entities.CarEntity

@Dao
interface CarDao {
    @Query("SELECT * FROM car_table")
    suspend fun getAllCars(): List<CarEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllCars(carList: List<CarEntity>)

    @Query("DELETE FROM car_table")
    suspend fun deleteCars()

    @Query("SELECT * FROM car_table WHERE favourite = 1")
    suspend fun getFavCars(): List<CarEntity>
}