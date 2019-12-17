package com.crypttp.android.parser.query_decoder

internal interface QueryDecoder {

    fun decodeDeepLinkQuery(query: String): String
}