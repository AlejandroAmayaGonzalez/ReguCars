package com.aamagon.regucars.core.extensions

import androidx.room.TypeConverter

/* Convert automatically between string and list
*  to store the color list into the database*/
class Converters{
    @TypeConverter
    fun stringForList(string: String): List<String> {
        return string.split(",").map { it.trim() }
    }

    @TypeConverter
    fun listToString(list: List<String>): String {
        return list.joinToString(",")
    }
}