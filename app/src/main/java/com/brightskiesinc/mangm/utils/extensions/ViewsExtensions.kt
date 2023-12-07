package com.brightskiesinc.mangm.utils.extensions

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.StringRes
import com.brightskiesinc.mangm.utils.MessageHelper
import com.brightskiesinc.mangm.presentation.base.BaseActivity
import com.google.android.material.snackbar.Snackbar
import kotlin.math.roundToInt

fun View.hide() {
    visibility = View.GONE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showKeyboard() {
    this.requestFocus()
    postDelayed({
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    }, 400)
}

fun View.hideKeyboard() {
    (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        .hideSoftInputFromWindow(windowToken, 0)
}

fun View.showSnackMessage(message: String, duration: Int = Snackbar.LENGTH_LONG) {
    findViewById<ViewGroup>(android.R.id.content).also {
        MessageHelper.showSnackMessage(it, message, duration)
    }
}

fun View.showSnackMessage(@StringRes messageRes: Int, duration: Int = Snackbar.LENGTH_LONG) {
    findViewById<ViewGroup>(android.R.id.content).also {
        MessageHelper.showSnackMessage(it, resources.getString(messageRes), duration)
    }
}

fun Int.toDp(): Int {
    return (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()
}

fun Int.toPx(): Int {
    return (this * Resources.getSystem().displayMetrics.density).roundToInt()
}

tailrec fun Context.getBaseActivity(): BaseActivity? = this as? BaseActivity
    ?: (this as? ContextWrapper)?.baseContext?.getBaseActivity()

