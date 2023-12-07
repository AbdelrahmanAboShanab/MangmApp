package com.brightskiesinc.mangm.utils.extensions

import android.content.Context

fun String.localized(context: Context, vararg args: String) =
    try {
        val packageName = context.packageName
        val identifier = context.resources
            .getIdentifier(this, "string", packageName)
        context.resources.getString(identifier, *args)
    } catch (e: Exception) {
        this
    }
