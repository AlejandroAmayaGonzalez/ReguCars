package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        YearPicker(states)
        Divider()
        ColorPicker(states)
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
fun YearPicker(states: States){
    // Control the dropdown
    var expanded = remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterTitle(stringResource(R.string.yearFilterTitle))
        Box {
            Text(
                text = if (states.selectedYear.intValue == 0)
                            stringResource(R.string.placeholderColor)
                       else states.selectedYear.intValue.toString(),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(120.dp)
                    .border(1.dp, Black)
                    .clickable { expanded.value = true }
                    .padding(8.dp)
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.height(200.dp)
            ) {
                states.yearOptions.forEach { year ->
                    DropdownMenuItem(
                        text = { Text(year.toString()) },
                        onClick = {
                            states.selectedYear.intValue = year
                            expanded.value = false
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ColorPicker(states: States){
    // Control the dropdown
    var expanded = remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterTitle(stringResource(R.string.colorFilterTitle))
        Box {
            Text(
                text = if (states.selectedColor.value.isNotEmpty())
                            states.selectedColor.value
                       else stringResource(R.string.placeholderColor),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .width(120.dp)
                    .border(1.dp, Black)
                    .clickable { expanded.value = true }
                    .padding(8.dp)
            )
            DropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false },
                modifier = Modifier.height(200.dp)
            ) {
                states.colorOptions.forEach { color ->
                    DropdownMenuItem(
                        text = { Text(color) },
                        onClick = {
                            states.selectedColor.value = color
                            expanded.value = false
                        }
                    )
                }
            }
        }
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
        modifier = Modifier.padding(Dimensions.default).fillMaxWidth()
    )
}