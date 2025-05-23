package com.aamagon.regucars.domain

import com.aamagon.regucars.core.extensions.toEntity
import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): User {
        val response = repository.getUsersFromApi()

        val random = (0..response.size -1).random()

        return if (response.isNotEmpty()){
            repository.clearUsers()

            repository.insertUsers(response.map { it.toEntity(id = response.indexOf(it)) })

            response[random]
        }else{
            val response = repository.getUsersFromDatabase()

            response[random]
        }
    }
}