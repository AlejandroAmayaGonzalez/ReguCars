package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.aamagon.regucars.ui.view.navigation.MainToolBar

@Composable
fun CarsScreen(navController: NavController){
    Scaffold (
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CarsScreenContent(modifier = Modifier.padding(innerPadding))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarsScreenContent(modifier: Modifier) {
    Scaffold (
        modifier = Modifier.padding()
    ) {
        Text(
            text = "Coches",
            fontSize = 50.sp,
            modifier = modifier.padding()
        )
    }
}