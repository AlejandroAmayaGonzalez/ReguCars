package com.aamagon.regucars.data.model

import com.google.gson.annotations.SerializedName

data class DataModel (
    @SerializedName("coches") val cars: List<CarModel>,
    @SerializedName("usuarios") val users: List<UserModel>
)