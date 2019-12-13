package com.crypttp.android.parser

internal interface QueryDecoder {

    fun decodeDeepLinkQuery(query: String): String
}