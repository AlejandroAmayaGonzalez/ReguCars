package com.aamagon.regucars.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("foto") val photo: String,
    @SerializedName("apodo") val nickname: String,
    @SerializedName("correo") val email: String,
    @SerializedName("nombre") val name: String,
    @SerializedName("fecha_nacimiento") val birth: String,
    @SerializedName("pais") val country: String
)