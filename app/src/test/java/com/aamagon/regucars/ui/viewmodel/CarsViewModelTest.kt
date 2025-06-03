package com.aamagon.regucars.ui.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.aamagon.regucars.domain.FiltersUseCase
import com.aamagon.regucars.domain.GetCarsUseCase
import com.aamagon.regucars.domain.GetFavCarsUseCase
import com.aamagon.regucars.domain.UpdateCarUseCase
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.view.screens.States
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CarsViewModelTest {

    @RelaxedMockK
    private lateinit var getCarsUseCase: GetCarsUseCase
    @RelaxedMockK
    private lateinit var getFavCarsUseCase: GetFavCarsUseCase
    @RelaxedMockK
    private lateinit var filtersUseCase: FiltersUseCase
    @RelaxedMockK
    private lateinit var updateCarUseCase: UpdateCarUseCase

    val context = mockk<Context>(relaxed = true)

    private lateinit var carsViewModel: CarsViewModel
    private val dispatcher = StandardTestDispatcher()

    // Create a rule to update livedata immediately while the test is running
    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    // Possible lists
    val allCars = listOf(
        Car(1,"Nissan",100,"gasolina",2004,listOf("rojo"),"fotoNissan",false),
        Car(2,"Honda",200,"diesel",2002,listOf("azul"),"fotoHonda",true)
    )

    val favCars = listOf(
        Car(2,"Honda",200,"diesel",2002,listOf("azul"),"fotoHonda",true)
    )

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `when the viewmodel is created, get data, set it and set biggest price`() = runBlocking {
        // Given
        coEvery { getCarsUseCase() } returns allCars
        coEvery { getFavCarsUseCase() } returns favCars

        // When
        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(carsViewModel.allCars.value == allCars)
        assert(carsViewModel.favCars.value == favCars)
        assert(carsViewModel.maxPrice == 200)
    }

    @Test
    fun `change between lists due to the boolean parameter`() = runBlocking {
        // Given
        coEvery { getCarsUseCase() } returns allCars
        coEvery { getFavCarsUseCase() } returns favCars

        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)

        // When
        dispatcher.scheduler.advanceUntilIdle()
        carsViewModel.showAList(true)

        // Then
        assert(carsViewModel.carList.value == favCars)
    }

    @Test
    fun `set the updated list in each livedata`() = runBlocking {
        // Given
        coEvery { getCarsUseCase() } returns allCars
        coEvery { getFavCarsUseCase() } returns favCars

        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)

        // When
        dispatcher.scheduler.advanceUntilIdle()
        carsViewModel.updateLists()

        // Then
        assert(carsViewModel.allCars.value == allCars)
        assert(carsViewModel.favCars.value == favCars)
    }

    @Test
    fun `change favourite field of an specific car`() = runBlocking {
        // Given
        val car = Car(1,"Nissan",100,"gasolina",2004,listOf("rojo"),"fotoNissan",false)

        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)

        // When
        carsViewModel.updateCar(car, true)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(car.isFavourite)
    }

    @Test
    fun `when there are applied filters, set the result, noMatches and original list value`() = runTest {
        // Given
        val testAllCars = listOf(Car(1,"Nissan",100,"gasolina",2004,listOf("rojo"),"fotoNissan",false))
        val testFavCars = listOf(Car(1,"Nissan",100,"gasolina",2004,listOf("rojo"),"fotoNissan",false))
        val states = States(context).apply {
            showFavs.value = false
        }

        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)

        // Set initial values for viewmodel fields
        carsViewModel.apply {
            this::class.java.getDeclaredField("allCars").apply {
                isAccessible = true
                set(carsViewModel, MutableLiveData(testAllCars))
            }
            this::class.java.getDeclaredField("favCars").apply {
                isAccessible = true
                set(carsViewModel, MutableLiveData(testFavCars))
            }
        }

        coEvery { filtersUseCase(states, testAllCars) } returns testFavCars

        // When
        carsViewModel.applyFilters(states)
        dispatcher.scheduler.advanceUntilIdle()

        // Then
        assert(testFavCars == carsViewModel.carList.value)
        assert(false == carsViewModel.noMatches.value)
    }

    @Test
    fun `reset the general list to original`() = runTest {
        // Given
        carsViewModel = CarsViewModel(getCarsUseCase, getFavCarsUseCase,
            filtersUseCase, updateCarUseCase)

        val allCarsList = carsViewModel.allCars.value ?: emptyList()

        carsViewModel.apply {
            this::class.java.getDeclaredField("original").apply {
                isAccessible = true
                set(carsViewModel, allCarsList)
            }
        }

        // When
        carsViewModel.resetList()

        // Then
        assert(allCarsList == carsViewModel.carList.value)
    }
}