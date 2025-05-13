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

@Composable
fun CarsScreen(navController: NavController){
    Scaffold (
        topBar = { ToolbarTitle(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CarsScreenContent(modifier = Modifier.padding(innerPadding))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarsScreenContent(modifier: Modifier) {
    val list = listOf(
        Car("Renault Clio",25900,"Gasolina"),
        Car("Toyota Corolla",18550,"Diésel"),
        Car("Dacia Sandero",13400,"Eléctrico"),
        Car("BMW M4",32800,"Eléctrico"),
        Car("Volkswagen Tiguan",34520,"Gasolina"),
        Car("Seat Cordoba",10800,"Diésel"),
        Car("Peugeot 308",27050,"Diésel"),
    )

    LazyColumn (
        modifier = modifier.padding(AppPadding.default)
    ) {
        items(list){ car ->
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