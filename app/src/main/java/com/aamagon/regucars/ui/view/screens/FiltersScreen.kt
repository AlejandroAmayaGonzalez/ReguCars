package com.aamagon.regucars.ui.view.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.aamagon.regucars.R
import com.aamagon.regucars.core.extensions.formatPrice
import com.aamagon.regucars.ui.theme.BackGroundTf
import com.aamagon.regucars.ui.theme.Black
import com.aamagon.regucars.ui.theme.CardColor
import com.aamagon.regucars.ui.theme.Dimensions
import com.aamagon.regucars.ui.theme.LibreBaskervilleFamily
import com.aamagon.regucars.ui.theme.LightBlue
import com.aamagon.regucars.ui.view.navigation.FiltersToolbar
import com.aamagon.regucars.ui.view.navigation.MainToolBar
import com.aamagon.regucars.ui.view.navigation.ToolBarRoutes
import com.aamagon.regucars.ui.viewmodel.CarsViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FiltersScreen(navController: NavController, carsViewModel: CarsViewModel, states: States){
    Scaffold (
        topBar = { FiltersToolbar(navController) },
        bottomBar = { MainToolBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        FiltersScreenContent(
            carsViewModel = carsViewModel,
            states = states,
            navController = navController,
            modifier = Modifier.padding(innerPadding).background(BackGroundTf)
        )
    }
}

@Composable
fun FiltersScreenContent(carsViewModel: CarsViewModel, states: States, navController: NavController, modifier: Modifier) {
    val scrollState = rememberScrollState()

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize().padding(Dimensions.default)
            .verticalScroll(scrollState)
    ){
        FuelFilter(states)
        Divider()
        PriceFilter(states, carsViewModel)
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
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CardColor,
                contentColor = Black
            )
        ){ Text( text = stringResource(R.string.applyFilters) ) }
        Button(
            onClick = {
                states.resetFilters()
                carsViewModel.resetList()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CardColor,
                contentColor = Black
            )
        ){ Text( text = stringResource(R.string.deleteFilters) ) }
    }
}

@Composable
fun YearPicker(states: States){
    // Control the dropdown
    val expanded = remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterTitle(stringResource(R.string.yearFilterTitle))
        Box {
            Text(
                text = if (states.selectedYear.intValue == 0)
                            stringResource(R.string.dropdownPlaceholder)
                       else states.selectedYear.intValue.toString(),
                fontSize = Dimensions.pickerSize,
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
    val expanded = remember { mutableStateOf(false) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        FilterTitle(stringResource(R.string.colorFilterTitle))
        Box {
            Text(
                text = states.selectedColor.value.ifEmpty { stringResource(R.string.dropdownPlaceholder) },
                fontSize = Dimensions.pickerSize,
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

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("DefaultLocale")
@Composable
fun PriceFilter(states: States, carsViewModel: CarsViewModel){

    states.maxSlider = carsViewModel.maxPrice

    Column ( modifier = Modifier.fillMaxSize() ) {
        FilterTitle(stringResource(R.string.priceFilterTitle))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
                .padding(start = Dimensions.default, end = Dimensions.default)
        ) {
            Text( text = stringResource(R.string.minPrice) )
            Text(
                text = stringResource(R.string.minPrice) + " - " +
                        states.sliderPos.floatValue.toInt().formatPrice()
            )
            Text( text = carsViewModel.maxPrice.formatPrice())
        }

        Slider(
            value = states.sliderPos.floatValue,
            onValueChange = { states.sliderPos.floatValue = it },
            // From 1€ to the most expensive bill
            valueRange = 1f..carsViewModel.maxPrice.toFloat(),
            colors = SliderDefaults.colors(
                activeTrackColor = LightBlue,
                inactiveTrackColor = CardColor
            ),
            thumb = {
                Image(
                    painter = painterResource(R.drawable.thumb_icon),
                    contentDescription = null,
                    modifier = Modifier.size(Dimensions.thumbSize)
                )
            },
            modifier = Modifier.padding(start = Dimensions.default, end = Dimensions.default)
        )
    }
}

@Composable
fun FilterTitle(title: String) {
    Text(
        text = title,
        fontSize = Dimensions.filterTitle,
        fontFamily = LibreBaskervilleFamily,
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
            colors = CheckboxDefaults.colors( checkedColor = CardColor)
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