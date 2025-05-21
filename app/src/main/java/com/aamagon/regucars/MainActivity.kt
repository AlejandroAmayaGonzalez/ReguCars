package com.aamagon.regucars

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aamagon.regucars.ui.navigation.ToolBarRoutes
import com.aamagon.regucars.ui.view.screens.HomeScreen
import com.aamagon.regucars.ui.view.screens.MyProfileScreen
import com.aamagon.regucars.ui.theme.ReguCarsTheme
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.screens.CarsScreen
import com.aamagon.regucars.ui.viewmodel.CarsViewModel
import com.aamagon.regucars.ui.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EnableHilt: Application()

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ReguCarsTheme {
                Surface (
                    color = MaterialTheme.colorScheme.background,
                    modifier = Modifier.Companion.fillMaxSize()
                ) {
                    MainApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun MainApp(
    carsViewModel: CarsViewModel = hiltViewModel(),
    userViewModel: UserViewModel = hiltViewModel()
){

    val navController = rememberNavController()

    Scaffold (
        bottomBar = { MainToolBar(navController) },
        content = {
            NavHost(
                navController = navController,
                startDestination = ToolBarRoutes.HomeScreen.route
            ) {
                composable(route = ToolBarRoutes.HomeScreen.route) { HomeScreen(navController) }
                composable(route = ToolBarRoutes.CarsScreen.route) {
                    CarsScreen(navController, carsViewModel)
                }
                composable(route = ToolBarRoutes.MyProfileScreen.route) {
                    MyProfileScreen(navController, userViewModel)
                }
            }
        }
    )
}