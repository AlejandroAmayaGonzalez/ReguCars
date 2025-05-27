package com.aamagon.regucars.core.extensions

import java.text.NumberFormat
import java.util.Locale

// Price with format "1.000"
fun Int.formatPrice(): String {
    return NumberFormat.getInstance(Locale.GERMANY).format(this) + "€"
}

// Return a list with this format "blue, white..."
fun List<String>.listToString(): String{
    return this.joinToString(", ")
}