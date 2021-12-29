package com.example.mescours1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView

class ActionDetailsActivity : AppCompatActivity() {
    lateinit var details_cours : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_action_details)

        val sharedPreferences1 = this.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
        val ecIntitule = sharedPreferences1.getString("ecIntitule", null)
        val nomCours = findViewById<TextView>(R.id.ecIntitule)
        nomCours.setText(ecIntitule)

        details_cours = findViewById(R.id.details_cours)
        details_cours.setOnClickListener {
            var dialog = DetailsCoursFragment()
            dialog.show(supportFragmentManager, "jkjlkjk")
        }
    }
}