package com.example.mescours1

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val r = Runnable {
            val sharedPreferences1: SharedPreferences = getSharedPreferences("user_information", Context.MODE_PRIVATE)
            val token = sharedPreferences1.getString("token", null)
            if(token !="" && token != null){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
        Handler(Looper.getMainLooper()).postDelayed(r, 3000)
    }
}