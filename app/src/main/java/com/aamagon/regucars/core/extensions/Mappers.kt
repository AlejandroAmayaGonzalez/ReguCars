package com.aamagon.regucars.core.extensions

import com.aamagon.regucars.data.model.CarModel
import com.aamagon.regucars.data.model.UserModel
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.domain.model.User

fun CarModel.toDomain() = Car(model, price, fuelType, year, colors, photo)

fun UserModel.toDomain() = User(photo, nickname, email, name, birth, country)