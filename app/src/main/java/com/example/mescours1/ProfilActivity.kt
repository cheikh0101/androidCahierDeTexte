package com.example.mescours1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class ProfilActivity : AppCompatActivity() {
    lateinit var deconnexion : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        deconnexion=findViewById(R.id.deconnexion)
        deconnexion.setOnClickListener{
            deconnexion()
        }
        val sharedPreferences1 = this
            .getSharedPreferences("user_information", Context.MODE_PRIVATE)

        val email = sharedPreferences1.getString("email", null)
        val email_profil = findViewById<TextView>(R.id.email_profil)
        email_profil.setText(email)

        val prenom = sharedPreferences1.getString("prenom", null)
        val nom = sharedPreferences1.getString("nom", null)
        val prenom_nom = findViewById<TextView>(R.id.prenom_nom)
        prenom_nom.setText(prenom + " " + nom)

        val telephone = sharedPreferences1.getString("telephone", null)
        val telephone_profil = findViewById<TextView>(R.id.telephone_profil)
        telephone_profil.setText(telephone)

        val matricule = sharedPreferences1.getString("matricule", null)
        val matricule_profil = findViewById<TextView>(R.id.matricule_profil)
        matricule_profil.setText(matricule)
    }

    fun deconnexion(){
        val sharedPreferences1 = this
            .getSharedPreferences("user_information", Context.MODE_PRIVATE)
        sharedPreferences1.edit().remove("token").apply()
        val intent = Intent(this, SplashScreenActivity::class.java)
        startActivity(intent)
        finish()
    }
}