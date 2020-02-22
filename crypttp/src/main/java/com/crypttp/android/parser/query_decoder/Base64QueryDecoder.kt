package com.crypttp.android.parser.query_decoder

import android.util.Base64

private const val PARAMS = "params="

internal class Base64QueryDecoder : QueryDecoder {

    override fun decodeDeepLinkQuery(query: String): String {
        return try {
            val decoded = Base64.decode(query.replace(PARAMS, ""), Base64.DEFAULT)
            String(decoded, Charsets.UTF_8)
        } catch (ex: Exception) {
            ""
        }
    }
}