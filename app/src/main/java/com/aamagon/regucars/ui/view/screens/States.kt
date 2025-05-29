package com.aamagon.regucars.ui.view.screens

import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class States @Inject constructor() {

    // State for showFavs in the toolbar
    var showFavs = mutableStateOf(false)

    // State for fuel filters
    var gasChecked = mutableStateOf(false)
    var dieselChecked = mutableStateOf(false)
    var electricChecked = mutableStateOf(false)
    var hybridChecked = mutableStateOf(false)

    fun resetFilters(){
        gasChecked.value = false
        dieselChecked.value = false
        electricChecked.value = false
        hybridChecked.value = false
    }
}