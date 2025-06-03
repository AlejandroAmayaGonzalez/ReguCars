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

class GetCarsUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var getCarsUseCase: GetCarsUseCase

    val res2 = listOf(
        Car(1,"Nissan",100,"gasolina",2004,listOf("rojo"),"fotoNissan",false),
        Car(2,"Honda",200,"diesel",2002,listOf("azul"),"fotoHonda",true)
    )
    val res1 = listOf(
        Car(4,"Citroen",400,"electrico",2010,listOf("verde"),"fotoCitroen",false)
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getCarsUseCase = GetCarsUseCase(repository)
    }

    @Test
    fun `when the api returns something but it's the same as the DB or it's not empty`() = runBlocking {
        // Given
        coEvery { repository.getCarsFromApi() } returns res2
        coEvery { repository.getCarsFromDatabase() } returns res2

        // When
        val response = getCarsUseCase()

        // Then
        assert(res2 == response)
    }

    @Test
    fun `when the api returns something but the stored data is different or it's empty`() = runBlocking {
        // Given
        coEvery { repository.getCarsFromApi() } returns res2
        coEvery { repository.getCarsFromDatabase() } returns res1

        // When
        val response = getCarsUseCase()

        // Then
        coVerify(exactly = 1) { repository.clearCars() }
        coVerify(exactly = 1) { repository.insertCars(any()) }
        assert(response == res2)
    }
}