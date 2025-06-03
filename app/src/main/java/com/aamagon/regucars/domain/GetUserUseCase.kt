package com.aamagon.regucars.domain

import com.aamagon.regucars.core.extensions.toEntity
import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): User {
        val apiResponse = repository.getUsersFromApi()

        val random: Int

        return if (apiResponse.isNotEmpty()){
            repository.clearUsers()

            repository.insertUsers(apiResponse.map { it.toEntity(id = apiResponse.indexOf(it)) })

            random = (0 until apiResponse.size).random()
            apiResponse[random]
        }else{
            val dbResponse = repository.getUsersFromDatabase()

            random = (0 until dbResponse.size).random()
            dbResponse[random]
        }
    }
}