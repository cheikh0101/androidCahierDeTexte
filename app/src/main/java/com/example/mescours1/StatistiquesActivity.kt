package com.example.mescours1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mescours1.api.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class StatistiquesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistiques)

        val sharedPreferences1 = this
            .getSharedPreferences("user_information", Context.MODE_PRIVATE)

        val id = sharedPreferences1.getInt("user_id", 0)
        nbreCours(id)
        nbreHeureCours(id)
        nbreSeance(id)
    }

    fun nbreCours(id : Int){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.professeurNombreCours(id);
            withContext(Dispatchers.Main) {
                try {
                    val nbreCours = findViewById<TextView>(R.id.nbreTotalCours)
                    nbreCours.setText("${response.body()?.nombreCours}")
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }

    fun nbreHeureCours(id : Int){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.professeurNombreHeureCours(id);
            withContext(Dispatchers.Main) {
                try {
                    val nbreHeure = findViewById<TextView>(R.id.nbreTotalHeureCours)
                    nbreHeure.setText("${response.body()?.heureCours}")
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }

    fun nbreSeance(id : Int){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.professeurNombreSeance(id);
            withContext(Dispatchers.Main) {
                try {
                    val nbreSeance = findViewById<TextView>(R.id.nbreTotalSeance)
                    nbreSeance.setText("${response.body()?.duree}")
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}