package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.aamagon.regucars.R
import com.aamagon.regucars.domain.model.User
import com.aamagon.regucars.ui.theme.AppPadding
import com.aamagon.regucars.ui.theme.Black
import com.aamagon.regucars.ui.theme.White
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.navigation.ToolbarTitle
import com.aamagon.regucars.ui.viewmodel.UserViewModel

@Composable
fun MyProfileScreen(navController: NavController, userViewModel: UserViewModel){
    Scaffold (
        topBar = { ToolbarTitle(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        MyProfileScreenContent(
            userViewModel = userViewModel,
            modifier = Modifier.padding(innerPadding)
        )
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyProfileScreenContent(
    userViewModel: UserViewModel,
    modifier: Modifier
) {
    // Access to user fields
    val user = userViewModel.user.observeAsState(User("","","","","",""))

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(AppPadding.default).fillMaxSize()
    ){
        AsyncImage(
            model = user.value.photo,
            contentDescription = null,
            modifier = Modifier.height(200.dp).width(200.dp)
                .clip(CircleShape)
        )

        Spacer( modifier = Modifier.height(20.dp) )

        ProfileTextField(stringResource(R.string.lbNickname), user.value.nickname)
        ProfileTextField(stringResource(R.string.lbEmail), user.value.email)
        ProfileTextField(stringResource(R.string.lbName), user.value.name)
        ProfileTextField(stringResource(R.string.lbBirth), user.value.birth)
        ProfileTextField(stringResource(R.string.lbCountry), user.value.country)
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