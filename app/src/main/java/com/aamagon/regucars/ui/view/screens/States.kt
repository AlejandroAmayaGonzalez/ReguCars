package com.aamagon.regucars.ui.view.screens

import android.content.Context
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import com.aamagon.regucars.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.LocalDate
import javax.inject.Inject
import kotlin.collections.toList

class States @Inject constructor(
    @ApplicationContext private val context: Context
) {

    // State for showFavs in the toolbar
    var showFavs = mutableStateOf(false)

    // State for fuel filters
    var gasChecked = mutableStateOf(false)
    var dieselChecked = mutableStateOf(false)
    var electricChecked = mutableStateOf(false)
    var hybridChecked = mutableStateOf(false)

    // State for manufacture year
    var selectedYear = mutableIntStateOf(0)

    // State for color
    var selectedColor = mutableStateOf("")

    // Options available for dropdown filters
    val yearOptions = (2000..LocalDate.now().year).toList().reversed()

    val colorOptions = listOf(
        context.getString(R.string.blue),
        context.getString(R.string.white),
        context.getString(R.string.black),
        context.getString(R.string.red),
        context.getString(R.string.green),
        context.getString(R.string.gray),
        context.getString(R.string.yellow),
        context.getString(R.string.silver),
    )

    fun resetFilters(){
        gasChecked.value = false
        dieselChecked.value = false
        electricChecked.value = false
        hybridChecked.value = false

        selectedYear.intValue = 0
        selectedColor.value = ""
    }
}