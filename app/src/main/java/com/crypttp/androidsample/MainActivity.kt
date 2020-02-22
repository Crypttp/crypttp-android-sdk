package com.crypttp.androidsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.crypttp.android.Crypttp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Crypttp.parseCrypttpDeepLink(
            intent
        ) {
            if (it != null) {

            }
        }
    }
}
