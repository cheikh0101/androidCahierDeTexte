package com.example.mescours1

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import android.provider.CalendarContract




class MainActivity : AppCompatActivity() {
    lateinit var cours: CardView;
    lateinit var agenda: CardView;
    lateinit var statistiques: CardView;
    lateinit var profil: CardView;
    lateinit var classe: CardView;

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
            val calendarUri = CalendarContract.CONTENT_URI
                .buildUpon()
                .appendPath("time")
                .build()
            startActivity(Intent(Intent.ACTION_VIEW, calendarUri))
        }

        /*classe = findViewById(R.id.classe)
        classe.setOnClickListener {
            val intent = Intent(this, ClasseActivity::class.java)
            startActivity(intent)
        }*/
    }
}