package com.aamagon.regucars.domain

import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.User
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetUserUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getUserUseCase: GetUserUseCase

    val res1 = listOf(
        User("foto1","apodo1","apodo@example.com","nombre","21/06/2000","España")
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getUserUseCase = GetUserUseCase(repository)
    }

    @Test
    fun `when the api returns something and it is not empty`() = runBlocking {
        // Given
        coEvery { repository.getUsersFromApi() } returns res1

        // When
        val response = getUserUseCase()

        // Then
        coVerify(exactly = 1) { repository.clearUsers() }
        coVerify(exactly = 1) { repository.insertUsers(any()) }
        assert(response == res1.first())
    }

    @Test
    fun `when the api returns emptyList then get from DB`() = runBlocking {
        // Given
        coEvery { repository.getUsersFromApi() } returns emptyList()
        coEvery { repository.getUsersFromDatabase() } returns res1

        // When
        val response = getUserUseCase()

        // Then
        coVerify(exactly = 1) { repository.getUsersFromDatabase() }
        assert(response == res1.first())
    }
}