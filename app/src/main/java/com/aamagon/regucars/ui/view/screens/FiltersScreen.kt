package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.regucars.R
import com.aamagon.regucars.ui.theme.Black
import com.aamagon.regucars.ui.theme.Dimensions
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.navigation.ToolBarRoutes
import com.aamagon.regucars.ui.view.navigation.ToolbarTitle
import com.aamagon.regucars.ui.viewmodel.CarsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FiltersScreen(navController: NavController, carsViewModel: CarsViewModel, states: States){
    Scaffold (
        topBar = { ToolbarTitle(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) {
        FiltersScreenContent(
            carsViewModel = carsViewModel,
            states = states,
            navController = navController
        )
    }
}

@Composable
fun FiltersScreenContent(carsViewModel: CarsViewModel, states: States, navController: NavController) {
    val scrollState = rememberScrollState()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(Dimensions.default)
            .verticalScroll(scrollState)
    ){
        FuelFilter(states)
        Divider()
        FilterButtons(navController, carsViewModel, states)
    }
}

@Composable
fun FuelFilter(states: States){
    Column (
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterTitle(stringResource(R.string.fuelFilterTitle))
        CheckBoxRow(states.gasChecked, stringResource(R.string.cbGasoline))
        CheckBoxRow(states.dieselChecked, stringResource(R.string.cbDiesel))
        CheckBoxRow(states.electricChecked, stringResource(R.string.cbElectric))
        CheckBoxRow(states.hybridChecked, stringResource(R.string.cbHybrid))
    }
}

@Composable
fun FilterButtons(navController: NavController, carsViewModel: CarsViewModel, states: States){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = {
                carsViewModel.applyFilters(states)
                navController.navigate(ToolBarRoutes.CarsScreen.route)
            }
        ){ Text( text = stringResource(R.string.applyFilters) ) }
        Button(
            onClick = {
                states.resetFilters()
                carsViewModel.resetList()
            }
        ){ Text( text = stringResource(R.string.deleteFilters) ) }
    }
}

@Composable
fun FilterTitle(title: String) {
    Text(
        text = title,
        fontSize = Dimensions.filterTitle,
        fontWeight = FontWeight.Bold
    )
}

@Composable
fun CheckBoxRow(state: MutableState<Boolean>, type: String){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = { state.value = !state.value })
    ) {
        Checkbox(
            checked = state.value,
            onCheckedChange = { state.value = it },
            colors = CheckboxDefaults.colors( checkedColor = Black )
        )
        Text( text = type )
    }
}

@Composable
fun Divider(){
    HorizontalDivider(
        color = Black,
        thickness = 1.dp,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    )
}