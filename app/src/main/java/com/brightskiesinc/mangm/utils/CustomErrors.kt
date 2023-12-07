package com.brightskiesinc.mangm.utils

import androidx.annotation.StringRes

sealed class NetworkErrors(val code: Int? = null, val message: String) {
    class NetworkError(code: Int? = null, message: String) : NetworkErrors(code, message)
    class Undefined(message: String) : NetworkErrors(message = message)
    class ValidationError(val validationType: String, message: String) :
        NetworkErrors(message = message)

    class AuthError(message: String) : NetworkErrors(message = message)
}

sealed class AppError {
    data class JustMessage(val errorMessage: String) : AppError()
    data class LoginRequired(val notifyUser: Boolean = true) : AppError()
    data class ValidationError(val validationType: String, val message: String) : AppError()
    object IgnoredError : AppError()
    class ResourceError(@StringRes val id: Int) : AppError()
}

