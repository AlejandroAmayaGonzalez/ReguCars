package com.aamagon.regucars.ui.view.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aamagon.regucars.R
import com.aamagon.regucars.ui.theme.Dimensions
import com.aamagon.regucars.ui.theme.LibreBaskervilleFamily
import com.aamagon.regucars.ui.theme.LightBlue
import com.aamagon.regucars.ui.theme.WinkySansFamily
import com.aamagon.regucars.ui.view.screens.States
import com.aamagon.regucars.ui.viewmodel.CarsViewModel

@Composable
fun MainToolBar(navController: NavController){
    BottomAppBar (
        containerColor = LightBlue,
        actions = {
            Row (
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.fillMaxWidth()
            ) {
                ToolBarIcon(
                    navigate = { navController.navigate(ToolBarRoutes.HomeScreen.route) },
                    icon = R.drawable.icon_home,
                    contentDesc = R.string.icon_home
                )
                ToolBarIcon(
                    navigate = { navController.navigate(ToolBarRoutes.CarsScreen.route) },
                    icon = R.drawable.icon_cars,
                    contentDesc = R.string.icon_cars
                )
                ToolBarIcon(
                    navigate = { navController.navigate(ToolBarRoutes.MyProfileScreen.route) },
                    icon = R.drawable.icon_profile,
                    contentDesc = R.string.icon_profile
                )
            }
        }
    )
}

@Composable
fun ToolBarIcon(
    navigate: () -> Unit,
    icon: Int,
    contentDesc: Int
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable(onClick = { navigate() })
    ) {
        IconButton( onClick = { navigate() } ) {
            Image(
                painter = painterResource(icon),
                contentDescription = stringResource(contentDesc),
                modifier = Modifier.size(Dimensions.toolbarIconSize)
            )
        }

        Text(
            text = stringResource(contentDesc),
            fontWeight = FontWeight.Bold,
            fontFamily = WinkySansFamily
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarTitle(navController: NavController){
    TopAppBar(
        title = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            Text(
                text = getTitle(currentRoute),
                fontWeight = FontWeight.Bold,
                fontFamily = LibreBaskervilleFamily,
                fontSize = 40.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = LightBlue)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarsToolbar(navController: NavController, carsViewModel: CarsViewModel, states: States){
    TopAppBar(
        title = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            Text(
                text = getTitle(currentRoute),
                fontWeight = FontWeight.Bold,
                fontFamily = LibreBaskervilleFamily,
                fontSize = 40.sp
            )
        },
        actions = {
            FavToggleButton(carsViewModel, states)
            Spacer(modifier = Modifier.width(Dimensions.default))
            IconButton( onClick = { navController.navigate(ToolBarRoutes.FiltersScreen.route) } ) {
                Image(
                    painter = painterResource(R.drawable.icon_filter),
                    contentDescription = stringResource(R.string.icon_filter),
                    modifier = Modifier.size(Dimensions.toolbarIconSize)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = LightBlue)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersToolbar(navController: NavController){
    TopAppBar(
        title = {
            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
            Text(
                text = getTitle(currentRoute),
                fontWeight = FontWeight.Bold,
                fontFamily = LibreBaskervilleFamily,
                fontSize = 40.sp
            )
        },
        actions = {
            IconButton( onClick = { navController.popBackStack() } ) {
                Image(
                    painter = painterResource(R.drawable.icon_close),
                    contentDescription = stringResource(R.string.icon_close),
                    modifier = Modifier.size(Dimensions.toolbarIconSize)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = LightBlue)
    )
}

@Composable
fun getTitle(route: String?): String {
    return when (route){
        "Inicio" -> stringResource(R.string.icon_home)
        "Coches" -> stringResource(R.string.icon_cars)
        "MiPerfil" -> stringResource(R.string.icon_profile)
        "Filtros" -> stringResource(R.string.icon_filter)
        else -> "App"
    }
}

@Composable
fun FavToggleButton(carsViewModel: CarsViewModel, states: States){

    val fav = R.drawable.icon_fav_filled_red
    val notFav = R.drawable.icon_fav_not_filled

    IconButton(
        // Change between favourites list and all cars list
        onClick = {
            states.showFavs.value = states.showFavs.value != true
            carsViewModel.changeNoMatchesValue(false)
            carsViewModel.showAList(states.showFavs.value)
        }
    ) {
        Image(
            painter = painterResource(if (states.showFavs.value) fav else notFav),
            contentDescription = stringResource(
                if (states.showFavs.value) R.string.addFav else R.string.delFav),
            modifier = Modifier.size(25.dp)
        )
    }
}
