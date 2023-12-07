package com.brightskiesinc.mangm.presentation.base

import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

abstract class BaseDialog<T : ViewBinding> : DialogFragment() {

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    private val baseActivity get() = activity as BaseActivity

    protected val navController by lazy { findNavController() }

    /**
     * Find the root view from the passed binding layout.
     * @param viewBinding is the binding class that generated
     * for the sub-class of this BaseDialog class
     *
     * @return root view of the passed layout
     */
    protected fun getView(viewBinding: T): View {
        _binding = viewBinding
        return binding.root
    }

//    protected fun showSnackMessage(
//        message: Int, duration: Int = Snackbar.LENGTH_LONG
//    ) = showSnackMessage(getString(message), duration)
//
//    protected fun showSnackMessage(
//        message: String, duration: Int = Snackbar.LENGTH_LONG
//    ) = baseActivity.showSnackbar(message, duration)

}