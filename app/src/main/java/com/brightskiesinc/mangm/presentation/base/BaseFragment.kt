package com.brightskiesinc.mangm.presentation.base

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.brightskiesinc.mangm.utils.UIMessageType
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private val baseActivity get() = activity as BaseActivity

    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    private var cacheView: Boolean = false

    protected var isViewCreated = _binding != null

    protected val navController by lazy { findNavController() }

    /**
     * Use the passed {viewBinding} object to find the root view and return it.
     *
     * @param viewBinding is the binding class that generated for the
     * sub-class of this com.brightskiesinc.mangmapp.presentation.base.BaseFragment class.
     *
     * @param cacheView indicates wither user want to save or cache the state of
     * this fragment view whenever he/she moves to another screen or not.
     *
     * @return root view of the passed layout.
     */
    protected fun getView(viewBinding: T, cacheView: Boolean = false): View {
        this.cacheView = cacheView
        _binding = viewBinding
        isViewCreated = true
        return binding.root
    }

    protected fun showLoading() = baseActivity.showLoading()

    protected fun hideLoading() = baseActivity.hideLoading()

    protected fun showSnackMessage(
        message: Int, duration: Int = Snackbar.LENGTH_LONG
    ) = showSnackMessage(getString(message), duration)

    protected fun showSnackMessage(
        message: String, duration: Int = Snackbar.LENGTH_LONG
    ) = baseActivity.showSnackbar(message, duration)

    private fun showSuccessMessage(
        message: String, duration: Int = Toast.LENGTH_LONG
    ) = Toast.makeText(requireActivity().applicationContext, message, duration).show()

    /**
     * LifeCycle safe version of BaseViewModel.doAfterDelay()
     * */
    fun doAfterDelay(delayTime: Long = 250, action: () -> Unit) {
        lifecycleScope.launch(Dispatchers.Default) {
            delay(delayTime)
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.RESUMED))
                withContext(Dispatchers.Main) { action.invoke() }
        }
    }

    override fun onDestroyView() {
        if (!cacheView)
            this._binding = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    // observe loading and message events from ViewModel
    protected open fun observeViewModelEvents(viewModel: BaseViewModel) {
        // UI shouldn’t be observing the UI state when the view isn’t being displayed to the user.
        // Start a coroutine in the lifecycle scope
        viewLifecycleOwner.lifecycleScope.launch {
            // repeatOnLifecycle launches the block in a new coroutine every time the
            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                // Trigger the flow and start listening for values.
                // Note that this happens when lifecycle is STARTED and stops
                // collecting when the lifecycle is STOPPED

                launch {
                    viewModel.loadingState.collect {
                        // New value received
                        if (it) showLoading() else hideLoading()
                    }
                }

                launch {
                    viewModel.messageState.collect {
                        it?.let {
                            when (it.type) {
                                UIMessageType.Error -> {
                                    baseActivity.showErrorMessage(it.message)
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

