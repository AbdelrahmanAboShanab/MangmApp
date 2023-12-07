package com.brightskiesinc.mangm.utils.extensions

import java.text.NumberFormat
import java.util.*


fun Number.toFormattedDecimal(
    minimumFractionsDigits: Int = 2, maximumFractionsDigits: Int = 2
): String {
    val formattedNumber = NumberFormat.getNumberInstance(Locale.ENGLISH)
    formattedNumber.maximumFractionDigits = maximumFractionsDigits
    formattedNumber.minimumFractionDigits = minimumFractionsDigits
    return formattedNumber.format(this)
}