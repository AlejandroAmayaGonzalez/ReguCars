package com.aamagon.regucars.domain

import android.content.Context
import android.util.Log
import com.aamagon.regucars.R
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.view.screens.States
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class FiltersUseCase @Inject constructor(
    @ApplicationContext private val context: Context
) {
    operator fun invoke(states: States, carList: List<Car>): List<Car> {

        val checkboxCategories = listOfNotNull(
            if (states.gasChecked.value) context.getString(R.string.cbGasoline) else null,
            if (states.dieselChecked.value) context.getString(R.string.cbDiesel) else null,
            if (states.electricChecked.value) context.getString(R.string.cbElectric) else null,
            if (states.hybridChecked.value) context.getString(R.string.cbHybrid) else null
        )

        var result = carList.asSequence()
            .filter {
                // Fuel filter
                if (checkboxCategories.isNotEmpty()) {
                    it.fuelType in checkboxCategories
                }else{ true }
            }.filter {
                if (states.selectedYear.intValue != 0){
                    it.year == states.selectedYear.intValue
                }else{ true }
            }.filter {
                if (states.selectedColor.value.isNotBlank()) {
                    it.colors.contains(states.selectedColor.value.lowercase())
                }else{ true }
            }

        Log.e("return list", "${result.toList()}")
        Log.e("color", states.selectedColor.value)
        return result.toList()
    }
}