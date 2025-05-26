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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aamagon.regucars.R
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.theme.AppPadding
import com.aamagon.regucars.ui.view.navigation.CarsToolbar
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.viewmodel.CarsViewModel

@Composable
fun CarsScreen(navController: NavController, carsViewModel: CarsViewModel, states: States){
    Scaffold (
        topBar = { CarsToolbar(navController, carsViewModel, states) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        CarsScreenContent(
            carsViewModel = carsViewModel,
            modifier = Modifier.padding(innerPadding)
        )

        var loading = carsViewModel.isLoading.observeAsState(false)

        if (loading.value){
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
    modifier: Modifier
) {

    val carList = carsViewModel.carList.observeAsState(emptyList())

    LazyColumn (
        modifier = modifier.padding(AppPadding.default)
    ) {
        items(carList.value){ car ->
            CarCard(car, carsViewModel.formattedPrice(car.price))
            Spacer( modifier = Modifier.height(AppPadding.default) )
        }
    }
}

@Composable
fun CarCard(car: Car, formattedPrice: String){
    Card {
        Row (
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {
            Box ( modifier = Modifier.height(AppPadding.sizeIcon) ) {
                AsyncImage(
                    model = car.photo,
                    contentDescription = null,
                    modifier = Modifier.height(AppPadding.sizeIcon)
                )
            }
            Box ( modifier = Modifier.weight(0.4f).height(AppPadding.sizeIcon) ) {
                Column (
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Text(
                        text = car.model,
                        textAlign = TextAlign.Center
                    )
                    Spacer( modifier = Modifier.height(10.dp) )
                    Text( text = "${formattedPrice}€" )
                }
            }
            Box (
                modifier = Modifier.weight(0.25f).height(AppPadding.sizeIcon)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier.fillMaxSize().align(Alignment.Center)
                ){
                    FavToggleButton(car.isFavourite)

                    Image(
                        painter = painterResource(R.drawable.arrow_more),
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun FavToggleButton(favState: Boolean){
    var isPressed = remember(favState) { mutableStateOf(favState) }
    val fav = R.drawable.icon_fav_filled_red
    val notFav = R.drawable.icon_fav_not_filled

    IconButton( onClick = { isPressed.value = !isPressed.value } ) {
        Image(
            painter = painterResource(if (isPressed.value) fav else notFav),
            contentDescription = stringResource(if (isPressed.value) R.string.addFav else R.string.delFav),
            modifier = Modifier.size(25.dp)
        )
    }
}