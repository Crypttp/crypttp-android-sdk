package com.crypttp.android.network.send_transaction_hash

import com.crypttp.android.network.NetworkConstants
import com.crypttp.android.network.NetworkConstants.METHOD_POST
import com.crypttp.android.network.base.BaseCommand
import com.crypttp.android.network.base.CommandParams
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.HttpURLConnection

internal class SendTransactionHashCommand(
    private val transactionId: String,
    private val transactionHash: String,
    private val gson: Gson
) : BaseCommand<Unit>() {

    override val params: CommandParams = CommandParams(
        NetworkConstants.TRANSACTION_HASH_URL,
        METHOD_POST
    )

    override fun executeInternal(connection: HttpURLConnection) {
        val request = SendTransactionHashRequest(transactionId, transactionHash)
        val response = StringBuffer()
        with(connection) {
            OutputStreamWriter(outputStream).use { output ->
                output.write(gson.toJson(request))
                output.flush()
            }

            InputStreamReader(inputStream).use { inputStream ->
                BufferedReader(inputStream).use { bufReader ->
                    var inputLine = bufReader.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = bufReader.readLine()
                    }
                }
            }
        }
        return
    }
}