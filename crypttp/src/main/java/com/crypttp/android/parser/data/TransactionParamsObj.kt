package com.crypttp.android.parser.data

import com.google.gson.annotations.SerializedName

data class TransactionParamsObj(
    @SerializedName("id") val transactionId: String?,
    @SerializedName("params") val params: List<Array<String>>?
)