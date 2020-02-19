package com.crypttp.android.parser

import android.content.Intent
import com.crypttp.android.domain.CrypttpTransactionInfo
import com.crypttp.android.domain.CrypttpTransactions
import com.crypttp.android.parser.data.TransactionParamsObj
import com.crypttp.android.parser.query_decoder.Base64QueryDecoder
import com.crypttp.android.parser.query_decoder.QueryDecoder
import com.crypttp.android.utils.getStringOrDefault
import com.google.gson.Gson

private const val HOST_CRYPTTP = "crypttp.com"
private const val SDK_SCHEME = "crypttp"

private const val COIN_INDEX = 0
private const val AMOUNT_INDEX = 1
private const val TO_INDEX = 2
private const val PAYLOAD_INDEX = 3
private const val MEMO_INDEX = 4
private const val SUCCESS_URL_INDEX = 5
private const val FAILURE_URL_INDEX = 6

internal class CrypttpDeepLinkParserImpl(
    private val gson: Gson
) : CrypttpDeepLinkParser {

    private val queryDecoder: QueryDecoder =
        Base64QueryDecoder()

    override fun parseDeepLink(intent: Intent?): CrypttpTransactions? {
        intent ?: return null
        val data = intent.data ?: return null
        val scheme = data.scheme ?: return null
        val host = data.host ?: return null
        val query = data.query ?: ""
        return parseInternal(scheme, host, query)
    }

    private fun parseInternal(scheme: String, host: String, query: String): CrypttpTransactions? {
        return if (host == null) {
            if (scheme == SDK_SCHEME) {
                parseDeepLinkParams(queryDecoder.decodeDeepLinkQuery(query))
            } else {
                null
            }
        } else {
            if (host != HOST_CRYPTTP) {
                parseDeepLinkParams(queryDecoder.decodeDeepLinkQuery(query))
            } else {
                null
            }
        }
    }

    private fun parseDeepLinkParams(query: String): CrypttpTransactions {
        val cryptoParams =
            gson.fromJson<TransactionParamsObj>(query, TransactionParamsObj::class.java)
        return CrypttpTransactions(
            cryptoParams.transactionId ?: "",
            cryptoParams.params?.map {
                parseTransactionArray(it)
            } ?: listOf()
        )
    }

    private fun parseTransactionArray(
        array: Array<String>
    ): CrypttpTransactionInfo {
        val coin = array.getStringOrDefault(COIN_INDEX)
        val amount = array.getStringOrDefault(AMOUNT_INDEX)
        val to = array.getStringOrDefault(TO_INDEX)
        val payload = array.getStringOrDefault(PAYLOAD_INDEX)
        val memo = array.getStringOrDefault(MEMO_INDEX)
        val onSuccessUrl = array.getStringOrDefault(SUCCESS_URL_INDEX)
        val onFailureUrl = array.getStringOrDefault(FAILURE_URL_INDEX)
        return CrypttpTransactionInfo(
            coin,
            amount,
            to,
            payload,
            memo,
            onSuccessUrl,
            onFailureUrl
        )
    }
}