package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.regucars.R
import com.aamagon.regucars.ui.theme.AppPadding
import com.aamagon.regucars.ui.theme.Black
import com.aamagon.regucars.ui.theme.White
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.navigation.ToolbarTitle

@Composable
fun MyProfileScreen(navController: NavController){
    Scaffold (
        topBar = { ToolbarTitle(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MyProfileScreenContent(modifier = Modifier.padding(innerPadding))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreenContent(modifier: Modifier) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(AppPadding.default).fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.icon_cars),
            contentDescription = null,
            modifier = Modifier.height(100.dp).width(100.dp)
        )

        Spacer( modifier = Modifier.height(20.dp) )

        ProfileTextField(stringResource(R.string.lbNickname), "Regu95")
        ProfileTextField(stringResource(R.string.lbEmail), "example@gmail.com")
        ProfileTextField(stringResource(R.string.lbName), "Alejandro Amaya")
        ProfileTextField(stringResource(R.string.lbBirth), "10/07/2004")
        ProfileTextField(stringResource(R.string.lbCountry), "España")
    }
}

@Composable
fun ProfileTextField(txLabel: String, tfValue: String){
    TextField(
        value = tfValue,
        onValueChange = {},
        label = { Text( text = txLabel, color = Black) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedIndicatorColor = Black,
        ),
        readOnly = true,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer( modifier = Modifier.height(20.dp) )
}