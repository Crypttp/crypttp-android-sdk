package com.crypttp.android.network.base

import com.crypttp.android.network.NetworkConstants
import com.crypttp.android.utils.wrapResult
import java.net.HttpURLConnection
import java.net.URL

internal abstract class BaseCommand<T> {

    abstract val params: CommandParams

    open val baseApiUrl: String = NetworkConstants.API_URL

    abstract fun executeInternal(connection: HttpURLConnection): T

    open fun prepareConnection(
        httpURLConnection: HttpURLConnection
    ): HttpURLConnection =
        httpURLConnection.apply {
            requestMethod = params.requestMethod
            params.requestHeaders.forEach {
                setRequestProperty(it.key, it.value)
            }
            doOutput = params.doOutput
            doInput = params.doInput
        }

    fun execute(): Response<T> {
        return wrapResult {
            val url = URL(baseApiUrl + params.requestPath)
            val connection = prepareConnection(url.openConnection() as HttpURLConnection)
            connection.connect()
            executeInternal(connection).apply {
                closeConnection(connection)
            }
        }
    }

    protected fun closeConnection(connection: HttpURLConnection) {
        try {
            connection.disconnect()
        } catch (ignored: Exception) {
        }
    }
}