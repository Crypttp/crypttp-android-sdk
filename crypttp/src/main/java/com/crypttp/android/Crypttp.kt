package com.crypttp.android

import android.content.Intent
import com.crypttp.android.domain.CrypttpTransactions
import com.crypttp.android.network.base.Response
import com.crypttp.android.network.send_transaction_hash.SendTransactionHashCommand
import com.crypttp.android.parser.CrypttpDeepLinkParser
import com.crypttp.android.parser.CrypttpDeepLinkParserImpl
import com.google.gson.GsonBuilder

object Crypttp {

    private val gson = GsonBuilder().create()

    private val deepLinkParser: CrypttpDeepLinkParser =
        CrypttpDeepLinkParserImpl(gson)

    fun parseCrypttpDeepLink(
        intent: Intent?,
        onDeepLinkResult: (CrypttpTransactions?) -> Unit
    ) {
        onDeepLinkResult(deepLinkParser.parseDeepLink(intent))
    }

    fun sendTransactionHashSync(
        transactionId: String,
        transactionHash: String
    ): Response<Unit> {
        return sendTransactionHashInternal(transactionId, transactionHash)
    }

    fun sendTransactionHashAsync(
        transactionId: String,
        transactionHash: String,
        onResultAction: (Response<Unit>) -> Unit
    ) {
        Thread {
            val response = sendTransactionHashInternal(transactionId, transactionHash)
            onResultAction(response)
        }.start()
    }

    private fun sendTransactionHashInternal(
        transactionId: String,
        transactionHash: String
    ): Response<Unit> {
        return SendTransactionHashCommand(
            transactionId,
            transactionHash,
            gson
        ).execute()
    }
}