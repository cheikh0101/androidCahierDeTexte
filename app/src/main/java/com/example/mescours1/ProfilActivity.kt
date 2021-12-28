package com.example.mescours1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ProfilActivity : AppCompatActivity() {
    lateinit var deconnexion : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        deconnexion=findViewById(R.id.deconnexion)
        deconnexion.setOnClickListener{
            deconnexion()
        }

    }

    fun deconnexion(){
        val sharedPreferences1 = this
            .getSharedPreferences("user_information", Context.MODE_PRIVATE)
        sharedPreferences1.edit().remove("token").commit()
        val intent = Intent(this, SplashScreenActivity::class.java)
        startActivity(intent)
    }
}