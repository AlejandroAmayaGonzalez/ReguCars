package com.aamagon.regucars.domain

import android.content.Context
import com.aamagon.regucars.R
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.view.screens.States
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class FiltersUseCaseTest {

    lateinit var filtersUseCase: FiltersUseCase

    val context = mockk<Context>(relaxed = true)

    val list = listOf(
        Car(1,"Nissan",100,"Gasolina",2004,listOf("rojo"),"fotoNissan",false),
        Car(2,"Honda",200,"Diésel",2002,listOf("azul"),"fotoHonda",true)
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        filtersUseCase = FiltersUseCase(context)
    }

    @Test
    fun `filter by fuel type`() = runTest {
        // Given
        coEvery { context.getString(R.string.cbGasoline) } returns "Gasolina"
        coEvery { context.getString(R.string.cbDiesel) } returns "Diésel"
        coEvery { context.getString(R.string.cbElectric) } returns "Eléctrico"
        coEvery { context.getString(R.string.cbHybrid) } returns "Híbrido"

        val states = States(context).apply {
            gasChecked.value = true
        }

        // When
        val response = filtersUseCase(states, list)

        // Then
        assertEquals(1, response.size)
    }

    @Test
    fun `filter by price`() = runTest {
        // Given
        val states = States(context).apply {
            sliderPos.floatValue = 100F
        }

        // When
        val response = filtersUseCase(states, list)

        // Then
        assertEquals(1, response.size)
    }

    @Test
    fun `filter by car color`() = runTest {
        // Given
        val states = States(context).apply {
            selectedColor.value = "azul"
        }

        // When
        val response = filtersUseCase(states, list)

        // Then
        assertEquals(1, response.size)
    }

    @Test
    fun `filter by year`() = runTest {
        // Given
        val states = States(context).apply {
            selectedYear.intValue = 2004
        }

        // When
        val response = filtersUseCase(states, list)

        // Then
        assertEquals(1, response.size)
    }
}