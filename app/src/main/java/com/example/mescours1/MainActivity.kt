package com.example.mescours1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {
    lateinit var cours: CardView;
    lateinit var agenda: CardView;
    lateinit var statistiques: CardView;
    lateinit var profil: CardView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        profil = findViewById(R.id.profil);
        profil.setOnClickListener{
            val intent = Intent(this, ProfilActivity::class.java)
            startActivity(intent)
        }

        cours = findViewById(R.id.cours)
        cours.setOnClickListener {
            val intent = Intent(this, CoursActivity::class.java)
            startActivity(intent)
        }

        statistiques = findViewById(R.id.statistiques)
        statistiques.setOnClickListener {
            val intent = Intent(this, StatistiquesActivity::class.java)
            startActivity(intent)
        }

        agenda = findViewById(R.id.agenda)
        agenda.setOnClickListener {
            val url = "https://www.google.com/intl/fr/calendar/about/"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }

    }
}