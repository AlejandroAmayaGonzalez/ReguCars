package com.aamagon.regucars.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamagon.regucars.domain.GetCarsUseCase
import com.aamagon.regucars.domain.model.Car
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarsViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
): ViewModel() {

    private val _carList = MutableLiveData<List<Car>>()
    val carList: LiveData<List<Car>> = _carList

    private val _isloading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isloading

    init {
        viewModelScope.launch {
            _isloading.postValue(true)

            val result = getCarsUseCase()

            if (result.isNotEmpty()){
                _carList.postValue(result)
            }

            _isloading.postValue(false)
        }
    }
}