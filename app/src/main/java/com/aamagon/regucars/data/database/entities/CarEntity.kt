package com.aamagon.regucars.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "car_table")
data class CarEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "model") val model: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "fuel_type") val fuelType: String,
    @ColumnInfo(name = "year") val year: Int,
    @ColumnInfo(name = "colors") val colors: List<String>,
    @ColumnInfo(name = "photo") val photo: String
)