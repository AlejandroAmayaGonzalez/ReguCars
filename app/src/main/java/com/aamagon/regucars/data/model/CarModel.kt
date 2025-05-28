package com.aamagon.regucars.data.model

import com.google.gson.annotations.SerializedName

data class CarModel (
    val id: Int,
    @SerializedName("modelo") val model: String,
    @SerializedName("precio") val price: Int,
    @SerializedName("combustible") val fuelType: String,
    @SerializedName("año") val year: Int,
    @SerializedName("colores") val colors: List<String>,
    @SerializedName("foto") val photo: String,
    @SerializedName("favorito") val isFavourite: Boolean
)