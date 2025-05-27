package com.aamagon.regucars.core.extensions

import java.text.NumberFormat
import java.util.Locale

// Price with "."
fun Int.formatPrice(): String {
    return NumberFormat.getInstance(Locale.GERMANY).format(this)
}