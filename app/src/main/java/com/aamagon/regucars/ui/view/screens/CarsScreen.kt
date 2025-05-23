package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.regucars.R
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.theme.AppPadding
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.navigation.ToolbarTitle
import com.aamagon.regucars.ui.viewmodel.CarsViewModel

@Composable
fun CarsScreen(navController: NavController, carsViewModel: CarsViewModel){
    Scaffold (
        topBar = { ToolbarTitle(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CarsScreenContent(
            carsViewModel = carsViewModel,
            modifier = Modifier.padding(innerPadding)
        )

        if (carsViewModel.isLoading.value == true){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarsScreenContent(
    carsViewModel: CarsViewModel,
    modifier: Modifier) {

    LazyColumn (
        modifier = modifier.padding(AppPadding.default)
    ) {
        items(carsViewModel.carList.value ?: emptyList()){ car ->
            CarCard(car)
            Spacer( modifier = Modifier.height(AppPadding.default) )
        }
    }
}

@Composable
fun CarCard(car: Car){
    val def = 100.dp

    Card {
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Box ( modifier = Modifier.height(def) ) {
                Image(
                    painter = painterResource(R.drawable.icon_cars),
                    contentDescription = null,
                    modifier = Modifier.width(def).height(def)
                )
            }
            Box ( modifier = Modifier.weight(0.4f).height(def) ) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text( text = car.model )
                    Text( text = "${car.price}€" )
                    Text( text = car.fuelType )
                }
            }
            Box (
                modifier = Modifier.weight(0.2f).height(def)
            ){
                Image(
                    painter = painterResource(R.drawable.arrow_more),
                    contentDescription = null,
                    modifier = Modifier.width(25.dp).height(25.dp).align(Alignment.Center)
                )
            }
        }
    }
}