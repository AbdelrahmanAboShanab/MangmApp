package com.brightskiesinc.mangm.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.brightskiesinc.mangm.utils.AppError
import com.brightskiesinc.mangm.utils.Resource
import com.brightskiesinc.mangm.utils.UIMessage
import com.brightskiesinc.mangm.utils.UIMessageType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BaseViewModel : ViewModel() {

    // Backing property to avoid state updates from other classes
    private val loadingStateFlow = MutableSharedFlow<Boolean>(1)

    // The UI collects from this SharedFlow to get its state updates
    val loadingState = loadingStateFlow.asSharedFlow()

    protected val messageStateFlow = MutableStateFlow<UIMessage?>(null)
    val messageState: StateFlow<UIMessage?> = messageStateFlow

    /**
     * Perform any action on Main thread after a specific delay time.
     *
     * @param delayTime you want to wait before performing
     * the action in milli seconds, default value is 200 ms.
     * @param action you want to perform or invoke.
     * */
    fun doAfterDelay(delayTime: Long = 250, action: () -> Unit) {
        viewModelScope.launch(Dispatchers.Default) {
            delay(delayTime)
            withContext(Dispatchers.Main) { action.invoke() }
        }
    }

    open suspend fun emitErrorUIMessage(appError: AppError) {
        hideLoading()

        val uiMessage = when (appError) {
            is AppError.JustMessage -> UIMessage(appError.errorMessage, UIMessageType.Error)
            is AppError.ValidationError -> UIMessage(appError.message, UIMessageType.Error)
            is AppError.ResourceError -> UIMessage("", UIMessageType.ResourceError(appError.id))
            else -> null
        }

        uiMessage?.let { messageStateFlow.update(it) }
    }

    open suspend fun <T> emitSuccessUIMessage(resourceSuccess: Resource.Success<T>) {
        hideLoading()

        val uiMessage = resourceSuccess.statusMessage?.let {
            UIMessage(it, UIMessageType.Success)
        }

        uiMessage?.let { messageStateFlow.update(it) }
    }

    fun onMessageConsumed(messageId: Long) {
        messageState.value?.let {
            if (it.id == messageId) messageStateFlow.value = null
        }
    }

    protected suspend fun showLoading() {
        loadingStateFlow.emit(true)
    }

    protected suspend fun hideLoading() {
        delay(400) //To avoid flashing
        loadingStateFlow.emit(false)
    }
}

fun <T> MutableStateFlow<T>.update(newValue: T) {
    this.value = newValue
}
