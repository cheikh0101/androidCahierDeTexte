package com.example.mescours1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailsCoursActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_cours)

        val sharedPreferences1 = this.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
        val ecIntitule = sharedPreferences1.getString("ecIntitule", null)
        val ecIntitul = findViewById<TextView>(R.id.ecIntitule)
        ecIntitul.setText("EC: " + ecIntitule)

        val nomSemestre = sharedPreferences1.getString("nomSemestre", null)
        val nomSemestr = findViewById<TextView>(R.id.nomSemestre)
        nomSemestr.setText("Semestre: " + nomSemestre)

        if(nomSemestre == "semestre 1" || nomSemestre == "semestre 2") {
            val nomNiveau = findViewById<TextView>(R.id.nomNiveau)
            nomNiveau.setText("Niveau: Licence 1")
        }else if(nomSemestre == "semestre 3" || nomSemestre == "semestre 4"){
            val nomNiveau = findViewById<TextView>(R.id.nomNiveau)
            nomNiveau.setText("Niveau: Licence 2")
        }else if(nomSemestre == "semestre 5" || nomSemestre == "semestre 6"){
            val nomNiveau = findViewById<TextView>(R.id.nomNiveau)
            nomNiveau.setText("Niveau: Licence 3")
        }

        val vHoraire = sharedPreferences1.getString("vHoraire", null)
        val vHorair = findViewById<TextView>(R.id.vHoraire)
        vHorair.setText("Volume Horaire: " + vHoraire +"H")

        val ueIntitule = sharedPreferences1.getString("ueIntitule", null)
        val ueIntitul = findViewById<TextView>(R.id.ueIntitule)
        ueIntitul.setText("UE: " + ueIntitule)
    }
}