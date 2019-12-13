package com.crypttp.android.network.send_transaction_hash

import com.google.gson.annotations.SerializedName

data class SendTransactionHashRequest(
    @SerializedName("id") val transactionId: String,
    @SerializedName("hash") val transactionHash: String
)