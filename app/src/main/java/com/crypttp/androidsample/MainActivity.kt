package com.crypttp.androidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crypttp.android.Crypttp
import com.crypttp.android.network.base.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Crypttp.sendTransactionHashAsync(
            "1234",
            "aashdh12h3h"
        ) {
            if (it is Response.Success) {

            }
        }
    }
}
