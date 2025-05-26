package com.aamagon.regucars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.regucars.domain.GetCarsUseCase
import com.aamagon.regucars.domain.GetFavCarsUseCase
import com.aamagon.regucars.domain.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.NumberFormat
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase,
    private val getFavCarsUseCase: GetFavCarsUseCase
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

    // Show a circular progress bar
    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

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

            _isloading.postValue(false)
        }
    }

    // Post a different list
    fun showAList(value: Boolean) {
        if (value){
            _carList.postValue(favCars.value)
        }else{
            _carList.postValue(allCars.value)
        }
    }

    // Formatted price using "."
    fun formattedPrice(price: Int): String {
        return NumberFormat.getInstance(Locale.GERMANY).format(price)
    }
}