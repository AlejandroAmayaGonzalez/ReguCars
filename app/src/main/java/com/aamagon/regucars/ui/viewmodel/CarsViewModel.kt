package com.aamagon.regucars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.regucars.domain.FiltersUseCase
import com.aamagon.regucars.domain.GetCarsUseCase
import com.aamagon.regucars.domain.GetFavCarsUseCase
import com.aamagon.regucars.domain.UpdateCarUseCase
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.view.screens.States
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val getFavCarsUseCase: GetFavCarsUseCase,
    private val filtersUseCase: FiltersUseCase,
    private val updateCarUseCase: UpdateCarUseCase
): ViewModel() {

    // All cars available
    private val _allCars = MutableLiveData<List<Car>>()
    val allCars: LiveData<List<Car>> = _allCars

    // Only favourite marked cars
    private val _favCars = MutableLiveData<List<Car>>()
    val favCars: LiveData<List<Car>> = _favCars

    // General list
    private val _carList = MutableLiveData<List<Car>>()
    val carList: LiveData<List<Car>> = _carList

    // State to alert UI for no results with that filters
    private val _noMatches = MutableLiveData<Boolean>(false)
    val noMatches: LiveData<Boolean> = _noMatches

    // Show a circular progress bar
    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    // List before filters
    private var original = emptyList<Car>()
    var maxPrice: Int = 0

    init {
        viewModelScope.launch {
            _isloading.postValue(true)

            // Load all cars
            val allCars = getCarsUseCase()

            if (allCars.isNotEmpty()){
                _allCars.postValue(allCars)
                _carList.postValue(allCars)
            }

            // Load favourites cars
            val favCars = getFavCarsUseCase()

            if (favCars.isNotEmpty()){
                _favCars.postValue(favCars)
            }else{
                _favCars.postValue(emptyList())
            }

            // Store the most expensive price
            maxPrice = getMaxPrice(allCars)

            _isloading.postValue(false)
        }
    }

    // Post a different list
    fun showAList(value: Boolean) {
        updateLists()

        if (value){
            _carList.postValue(favCars.value)
        }else{
            _carList.postValue(allCars.value)
        }
    }

    // Update DB to mark it as favourite
    fun updateCar(car: Car, value: Boolean) = viewModelScope.launch {
        car.isFavourite = value
        updateCarUseCase(car)

        updateLists()
    }

    // Store the most updated data
    fun updateLists() = viewModelScope.launch {
        _allCars.postValue(getCarsUseCase())
        _favCars.postValue(getFavCarsUseCase())
    }

    // Store the most expensive car to assign it as the maximum slider
    private fun getMaxPrice(list: List<Car>): Int{
        if (list.isNotEmpty()){
            val size = list.size - 1
            var res = 0

            for (i in 0..size){
                var value = list[i].price
                if (value > res) res = value
            }

            return res
        }

        return 1
    }

    // Apply filters
    fun applyFilters(states: States) {
        // Store the used list to filter
        original = if (states.showFavs.value){
            favCars.value ?: emptyList()
        }else{
            allCars.value ?: emptyList()
        }

        // Post result to show it
        val result = filtersUseCase(states, original)
        _carList.postValue(result)

        if (result.isEmpty()) {
            changeNoMatchesValue(true)
        }else {
            changeNoMatchesValue(false)
        }
    }

    fun changeNoMatchesValue(value: Boolean) = _noMatches.postValue(value)
    fun resetList() = _carList.postValue(original)
}