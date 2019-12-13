package com.crypttp.android.utils

import com.crypttp.android.network.base.Response

internal fun <T> Array<T>.getOrDefault(index: Int, default: T): T {
    return if (index >= size) {
        default
    } else {
        get(index)
    }
}

internal fun Array<String>.getStringOrDefault(index: Int, default: String = ""): String =
    getOrDefault(index, default)

internal inline fun <T> wrapResult(crossinline action: () -> T): Response<T> {
    return try {
        Response.Success(action())
    } catch (ex: Exception) {
        Response.Failure(ex)
    }
}