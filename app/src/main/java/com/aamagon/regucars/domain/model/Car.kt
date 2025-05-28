package com.aamagon.regucars.domain.model

data class Car (
    val id: Int,
    val model: String,
    val price: Int,
    val fuelType: String,
    val year: Int,
    val colors: List<String>,
    val photo: String,
    var isFavourite: Boolean
)