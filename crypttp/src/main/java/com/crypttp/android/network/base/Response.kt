package com.crypttp.android.network.base

sealed class Response<T> {

    data class Success<T>(val data: T) : Response<T>()
    data class Failure<T>(val error: Throwable) : Response<T>()
}