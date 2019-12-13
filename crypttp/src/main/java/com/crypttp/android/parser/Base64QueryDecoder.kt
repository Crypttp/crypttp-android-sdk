package com.crypttp.android.parser

import android.util.Base64

private const val PARAMS = "params="

internal class Base64QueryDecoder : QueryDecoder {

    override fun decodeDeepLinkQuery(query: String): String {
        val decoded = Base64.decode(query.replace(PARAMS, ""), Base64.DEFAULT)
        return String(decoded, Charsets.UTF_8)
    }
}