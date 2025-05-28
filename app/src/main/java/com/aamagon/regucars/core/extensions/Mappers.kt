package com.aamagon.regucars.core.extensions

import com.aamagon.regucars.data.database.entities.CarEntity
import com.aamagon.regucars.data.database.entities.UserEntity
import com.aamagon.regucars.data.model.CarModel
import com.aamagon.regucars.data.model.UserModel
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.domain.model.User

// Data model to Domain
fun CarModel.toDomain(id: Int) = Car(id, model, price, fuelType, year, colors, photo, isFavourite)
fun UserModel.toDomain() = User(photo, nickname, email, name, birth, country)

// Entities to Domain
fun CarEntity.toDomain(id: Int) = Car(id, model, price, fuelType, year, colors, photo, isFavourite)
fun UserEntity.toDomain() = User(photo, nickname, email, name, birth, country)

// Domain to Entity
fun Car.toEntity(id: Int): CarEntity {
    return CarEntity(
        id = id,
        model = model,
        price = price,
        fuelType = fuelType,
        year = year,
        colors = colors,
        photo = photo,
        isFavourite = isFavourite
    )
}

fun User.toEntity(id: Int): UserEntity {
    return UserEntity(
        id = id,
        photo = photo,
        nickname = nickname,
        email = email,
        name = name,
        birth = birth,
        country = country
    )
}