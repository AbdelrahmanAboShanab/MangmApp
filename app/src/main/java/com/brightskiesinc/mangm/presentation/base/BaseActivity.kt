package com.brightskiesinc.mangm.presentation.base

import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.brightskiesinc.mangm.utils.MessageHelper
import com.brightskiesinc.mangm.utils.extensions.localized
import com.brightskiesinc.mangmapp.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy { createLoadingDialog() }

    fun showLoading() {
        if (!loadingDialog.isShowing) {
            loadingDialog.show()
            loadingDialog.window?.setBackgroundDrawable(null)
        }
    }

    fun hideLoading() {
        if (loadingDialog.isShowing)
            loadingDialog.dismiss()
    }

    fun showSnackbar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        window.findViewById<ViewGroup>(android.R.id.content).also {
            MessageHelper.showSnackMessage(it, message, duration)
        }
    }

    fun showErrorMessage(message: String) {
        window.findViewById<ViewGroup>(android.R.id.content).also {
            MessageHelper.showSnackMessage(it, message.localized(this))
        }
    }

    fun showSuccessMessage(message: String) {
        window.findViewById<ViewGroup>(android.R.id.content).also {
            MessageHelper.showSnackMessage(it, message.localized(this), isSuccess = true)
        }
    }

    private fun createLoadingDialog(): AlertDialog {
        return AlertDialog.Builder(this).apply {
            setView(layoutInflater.inflate(R.layout.layout_loading, null))
            setCancelable(false)
        }.create()
    }

}