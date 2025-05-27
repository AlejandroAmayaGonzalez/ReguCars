package com.aamagon.regucars.ui.view.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.aamagon.regucars.R
import com.aamagon.regucars.core.extensions.formatPrice
import com.aamagon.regucars.core.extensions.listToString
import com.aamagon.regucars.domain.model.Car
import com.aamagon.regucars.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarDialog(show: Boolean, car: Car, onDismiss: () -> Unit){
    if (show){
        AlertDialog(
            onDismissRequest = { onDismiss() },
            confirmButton = {},
            dismissButton = {
                TextButton( onClick = { onDismiss() } ) {
                    Text(
                        text = stringResource(R.string.dismissButton),
                        fontSize = Dimensions.contentDialog
                    )
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.titleDialog) + car.model,
                    fontWeight = FontWeight.Bold,
                    fontSize = Dimensions.titleDialog
                )
            },
            text = { CarDialogContent(car) }
        )
    }
}

@Composable
fun CarDialogContent(car: Car){
    // Allows to scroll
    val scrollState = rememberScrollState()

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        AsyncImage(
            model = car.photo,
            contentDescription = car.model,
            modifier = Modifier.size(300.dp)
        )

        TextLine(stringResource(R.string.model), car.model)
        TextLine(stringResource(R.string.price), car.price.formatPrice())
        TextLine(stringResource(R.string.year), car.year.toString())
        TextLine(stringResource(R.string.fuelType), car.fuelType)
        TextLine(stringResource(R.string.colors), car.colors.listToString())
        TextLine(stringResource(R.string.isFav),
            if (car.isFavourite) stringResource(R.string.yes) else stringResource(R.string.no))
    }
}

@Composable
fun TextLine(template: String, fieldValue: String){
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ){
        Text(
            text = template,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = Dimensions.contentDialog
        )
        Text(
            text = fieldValue,
            textAlign = TextAlign.Center,
            fontSize = Dimensions.contentDialog
        )
    }
    Spacer( modifier = Modifier.height(Dimensions.default))
}