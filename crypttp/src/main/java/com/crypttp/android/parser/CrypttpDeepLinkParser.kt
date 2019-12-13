package com.crypttp.android.parser

import android.content.Intent
import com.crypttp.android.domain.CrypttpTransactions

internal interface CrypttpDeepLinkParser {

    fun parseDeepLink(intent: Intent?): CrypttpTransactions?
}