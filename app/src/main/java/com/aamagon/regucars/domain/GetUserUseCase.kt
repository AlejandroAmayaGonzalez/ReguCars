package com.aamagon.regucars.domain

import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): User {
        val response = repository.getUsersFromApi()

        val random = (0..response.size -1).random()

        return response[random]
    }
}