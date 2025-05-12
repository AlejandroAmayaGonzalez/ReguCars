package com.aamagon.regucars.ui.screens

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
fun MyProfileScreen(navController: NavController){
    Scaffold (
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MyProfileScreenContent(modifier = Modifier.padding(innerPadding))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreenContent(modifier: Modifier) {
    Scaffold (
        modifier = Modifier.padding()
    ) {
        Text(
            text = "Mi Perfil",
            fontSize = 50.sp,
            modifier = modifier.padding()
        )
    }
}