package com.brightskiesinc.mangm.utils


sealed class Resource<out T> {
    class Success<T>(val data: T, val statusMessage: String? = null, val totalCount: Int? = null) :
        Resource<T>()

    class Error<T>(val appError: AppError) : Resource<T>()
}

suspend fun <T : Any> Resource<T>.onSuccess(
    executable: suspend (T) -> Unit
): Resource<T> = apply {
    if (this is Resource.Success) {
        executable(data)
    }
}

suspend fun <T : Any> Resource<T>.onSuccessResource(
    executable: suspend (Resource.Success<T>) -> Unit
): Resource<T> = apply {
    if (this is Resource.Success) {
        executable(this)
    }
}

suspend fun <T : Any> Resource<T>.onError(
    executable: suspend (error: AppError) -> Unit
): Resource<T> = apply {
    if (this is Resource.Error) {
        executable(appError)
    }
}