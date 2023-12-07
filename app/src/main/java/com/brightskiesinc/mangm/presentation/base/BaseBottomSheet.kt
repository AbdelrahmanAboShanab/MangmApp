package com.brightskiesinc.mangm.presentation.base

import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.brightskiesinc.mangm.utils.UIMessageType
import com.brightskiesinc.mangm.utils.autoCleared
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch

abstract class BaseBottomSheet<T : ViewBinding> : BottomSheetDialogFragment() {

    protected var binding by autoCleared<T>()

    private val baseActivity get() = activity as BaseActivity

    protected val navController by lazy { findNavController() }

    /**
     * Find the root view from the passed binding layout.
     * @param viewBinding is the binding class that generated
     * for the sub-class of this BaseBottomSheet class
     *
     * @return root view of the passed layout
     */
    protected fun getView(viewBinding: T): View {
        binding = viewBinding
        return binding.root
    }

    protected fun showSnackMessage(
        message: Int, duration: Int = Toast.LENGTH_LONG
    ) = showSnackMessage(getString(message), duration)

    private fun showSnackMessage(
        message: String, duration: Int = Toast.LENGTH_LONG
    ) = Toast.makeText(requireActivity().applicationContext, message, duration).show()

    private fun showSuccessMessage(
        message: String, duration: Int = Toast.LENGTH_LONG
    ) = Toast.makeText(requireActivity().applicationContext, message, duration).show()

    // observe loading and message events from ViewModel
    protected open fun observeViewModelEvents(viewModel: BaseViewModel) {
        // UI shouldn’t be observing the UI state when the view isn’t being displayed to the user.
        // Start a coroutine in the lifecycle scope
        lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED

                launch {
                    viewModel.loadingState.collect {
                        // New value received
                        if (it) baseActivity.showLoading() else baseActivity.hideLoading()
                    }
                }

                launch {
                    viewModel.messageState.collect {
                        it?.let {
                            when (it.type) {
                                UIMessageType.Error -> {
                                    showSnackMessage(it.message)
                                    viewModel.onMessageConsumed(it.id)
                                }
                                UIMessageType.Success -> {
                                    showSuccessMessage(it.message)
                                    viewModel.onMessageConsumed(it.id)
                                }
                                is UIMessageType.ResourceError -> {
                                    showSnackMessage(it.type.id)
                                    viewModel.onMessageConsumed(it.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}