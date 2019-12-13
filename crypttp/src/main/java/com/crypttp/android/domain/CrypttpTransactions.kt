package com.crypttp.android.domain

data class CrypttpTransactions(
    val transactionId: String,
    val transactions: List<CrypttpTransactionInfo>
)

data class CrypttpTransactionInfo(
    val coin: String,
    val amount: String,
    val to: String,
    val payload: String,
    val memo: String,
    val onSuccessUrl: String,
    val onFailureUrl: String
)