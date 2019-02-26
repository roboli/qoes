package com.services.roboli.qoes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    var phoneNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        connectButton(findViewById(R.id.btn_1))
        connectButton(findViewById(R.id.btn_2))
        connectButton(findViewById(R.id.btn_3))
        connectButton(findViewById(R.id.btn_4))
        connectButton(findViewById(R.id.btn_5))
        connectButton(findViewById(R.id.btn_6))
        connectButton(findViewById(R.id.btn_7))
        connectButton(findViewById(R.id.btn_8))
        connectButton(findViewById(R.id.btn_9))
        connectButton(findViewById(R.id.btn_0))
    }

    fun connectButton(btn: TextView) {
        btn.setOnClickListener { v -> btnClicked(v) }
    }

    fun btnClicked(view: View) {
        when (view.id) {
            R.id.btn_1 ->  phoneNumber += "1"
            R.id.btn_2 ->  phoneNumber += "2"
            R.id.btn_3 ->  phoneNumber += "3"
            R.id.btn_4 ->  phoneNumber += "4"
            R.id.btn_5 ->  phoneNumber += "5"
            R.id.btn_6 ->  phoneNumber += "6"
            R.id.btn_7 ->  phoneNumber += "7"
            R.id.btn_8 ->  phoneNumber += "8"
            R.id.btn_9 ->  phoneNumber += "9"
            else -> phoneNumber += "0"
        }

        (findViewById<TextView>(R.id.phone)).setText(phoneNumber)
    }
}
