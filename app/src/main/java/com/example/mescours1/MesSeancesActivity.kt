package com.example.mescours1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.SeanceX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class MesSeancesActivity : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mes_seances)

        newRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        val sharedPreferences1 = this.getSharedPreferences("user_information", Context.MODE_PRIVATE)
        val user_id = sharedPreferences1.getInt("user_id", 2)
        val sharedPreferences = this.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
        val cours_id = sharedPreferences.getInt("id",0)
        listeSeance(user_id,cours_id)
    }

    private fun listeSeance(x: Int, y: Int){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.professeurSeance(x,y);
            withContext(Dispatchers.Main) {
                try {
                    println(response.body())
                    val adapter = ListeSeance(response.body()!!.seance as ArrayList<SeanceX>)
                    newRecyclerView.adapter = adapter
                    val seance = findViewById<View>(R.id.seance) as TextView
                    val nbreSeance = adapter.itemCount
                    seance.setText("Séances (" +nbreSeance+ ")")
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}