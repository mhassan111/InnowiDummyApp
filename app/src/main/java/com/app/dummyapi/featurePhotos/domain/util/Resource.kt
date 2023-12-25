package com.app.dummyapi.featurePhotos.domain.util

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: Throwable? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String?, exception: Throwable? = null) :
        Resource<T>(message = message, exception = exception)
}