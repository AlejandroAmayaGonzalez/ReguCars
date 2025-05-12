package com.aamagon.regucars.ui.view.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
        horizontalAlignment = Alignment.CenterHorizontally
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