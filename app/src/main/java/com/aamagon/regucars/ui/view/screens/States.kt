package com.aamagon.regucars.ui.view.screens

import androidx.compose.runtime.mutableStateOf
import javax.inject.Inject

class States @Inject constructor() {

    // State for showFavs in the toolbar
    var showFavs = mutableStateOf(false)

}