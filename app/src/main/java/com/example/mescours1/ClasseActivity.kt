package com.example.mescours1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

import android.content.Intent

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.net.Uri
import android.telephony.SmsManager
import android.widget.Button
import java.lang.Exception


class ClasseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classe)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "771116122"))
            startActivity(intent)
        }
    }
}