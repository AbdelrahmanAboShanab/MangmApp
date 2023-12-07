package com.brightskiesinc.mangm.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import com.brightskiesinc.mangm.utils.extensions.show
import com.brightskiesinc.mangmapp.R
import com.google.android.material.snackbar.Snackbar

object MessageHelper {

    fun showSnackMessage(
        focusedView: ViewGroup, message: String, duration: Int = Snackbar.LENGTH_LONG,
        isSuccess: Boolean = false,
        hasAction: Boolean = true, action: (() -> Unit)? = null,
        @StringRes actionButtonTextId: Int = R.string.ok
    ) {

        val inflater = LayoutInflater.from(focusedView.context)
        val content =
            if (isSuccess) inflater.inflate(R.layout.app_snackbar_success, focusedView, false)
            else inflater.inflate(R.layout.app_snackbar, focusedView, false)

        content.apply { (findViewById<View>(R.id.snackBarTextView) as TextView).text = message }

        val snackBar = Snackbar.make(focusedView, message, duration)

        (snackBar.view as Snackbar.SnackbarLayout).apply {
            setPadding(0, 0, 0, 0)
            addView(content)
        }

        if (hasAction && !isSuccess)
            (content.findViewById<View>(R.id.snackBarAction) as TextView).apply {
                setText(actionButtonTextId)
                setOnClickListener {
                    snackBar.dismiss()
                    action?.invoke()
                }
            }.show()

        snackBar.show()
    }

}