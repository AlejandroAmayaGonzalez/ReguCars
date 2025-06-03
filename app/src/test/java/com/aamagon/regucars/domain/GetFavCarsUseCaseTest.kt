package com.aamagon.regucars.domain

import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.Car
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetFavCarsUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getFavCarsUseCase: GetFavCarsUseCase

    val res = listOf(
        Car(2,"Honda",200,"diesel",2002,listOf("azul"),"fotoHonda",true)
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getFavCarsUseCase = GetFavCarsUseCase(repository)
    }

    @Test
    fun `call repository to get favourite list from database`() = runBlocking {
        // Given
        coEvery { repository.getFavCars() } returns res

        // When
        val response = getFavCarsUseCase()

        // Then
        coVerify(exactly = 1) { repository.getFavCars() }
        assert(response == res)
    }
}