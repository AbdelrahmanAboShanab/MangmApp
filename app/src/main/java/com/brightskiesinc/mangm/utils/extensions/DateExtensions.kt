package com.brightskiesinc.mangm.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toFormattedDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("dd MMMM yyyy, hh:mm", Locale.ENGLISH)
        simpleDateFormat.format(this * 1000L)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Long.toNotificationFormattedDate(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("dd MMM yy, hh:mm a", Locale.ENGLISH)
        simpleDateFormat.format(this * 1000L)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}

fun Long.toFormattedTime(): String {
    return try {
        val simpleDateFormat = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
        simpleDateFormat.format(this * 1000L)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}
