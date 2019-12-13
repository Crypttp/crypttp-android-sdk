package com.crypttp.android.network.base

data class CommandParams(
    val requestPath: String,
    val requestMethod: String,
    val requestHeaders: Map<String, String> = mapOf(
        "Content-Type" to "application/json"
    ),
    val doInput: Boolean = true,
    val doOutput: Boolean = true
)