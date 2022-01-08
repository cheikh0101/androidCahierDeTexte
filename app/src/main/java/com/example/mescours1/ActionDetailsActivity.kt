package com.example.mescours1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView

class ActionDetailsActivity : AppCompatActivity() {
    lateinit var details_cours : CardView
    lateinit var creer_seance : CardView
    lateinit var mes_seances : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_details)

        val sharedPreferences1 = this.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
        val ecIntitule = sharedPreferences1.getString("ecIntitule", null)
        val nomCours = findViewById<TextView>(R.id.ecIntitule)
        nomCours.setText(ecIntitule)

        details_cours = findViewById(R.id.details_cours)
        details_cours.setOnClickListener {
            val intent = Intent(this, DetailsCoursActivity::class.java)
            startActivity(intent)
        }

        creer_seance = findViewById(R.id.creer_seance)
        creer_seance.setOnClickListener {
            val intent = Intent(this, CreationSeanceActivity::class.java)
            startActivity(intent)
        }

        mes_seances = findViewById(R.id.mes_seances)
        mes_seances.setOnClickListener {
            val intent = Intent(this, MesSeancesActivity::class.java)
            startActivity(intent)
        }
    }
}