package com.aamagon.regucars.domain

import com.aamagon.regucars.data.Repository
import com.aamagon.regucars.domain.model.Car
import io.mockk.MockKAnnotations
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.just
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class UpdateCarUseCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var updateCarUseCase: UpdateCarUseCase

    val res = listOf(
        Car(2,"Honda",200,"diesel",2002,listOf("azul"),"fotoHonda",true)
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        updateCarUseCase = UpdateCarUseCase(repository)
    }

    @Test
    fun `call repository then db to change the car boolean field`() = runBlocking {
        // Given
        coEvery { repository.updateCar(res.first()) } just Runs

        // Then
        updateCarUseCase(res.first())

        // Given
        coVerify(exactly = 1) { repository.updateCar(res.first()) }
    }
}