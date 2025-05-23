package com.aamagon.regucars.ui.view.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.aamagon.regucars.R
import com.aamagon.regucars.ui.navigation.ToolBarRoutes

@Composable
fun MainToolBar(navController: NavController){
    BottomAppBar (
        containerColor = Color.LightGray,
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
                modifier = Modifier.height(100.dp).width(100.dp)
            )
        }

        Text( text = stringResource(contentDesc) )
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
                fontSize = 40.sp
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.LightGray)
    )
}

@Composable
fun getTitle(route: String?): String {
    return when (route){
        "Inicio" -> stringResource(R.string.icon_home)
        "Coches" -> stringResource(R.string.icon_cars)
        "MiPerfil" -> stringResource(R.string.icon_profile)
        else -> "App"
    }
}
