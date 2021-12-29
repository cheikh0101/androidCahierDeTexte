package com.example.mescours1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.CourX
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CoursActivity : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<CourX>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cours)

        newRecyclerView = findViewById<View>(R.id.recyclerView) as RecyclerView
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        val sharedPreferences1 = this
            .getSharedPreferences("user_information", Context.MODE_PRIVATE)
        val user_id = sharedPreferences1.getInt("user_id",1)
        professeurCours(user_id)
    }

    private fun professeurCours(x: Int){
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.professeurCours(x);
            withContext(Dispatchers.Main) {
                try {
                    val adapter = ListCours(response.body()!!.cours as ArrayList<CourX>)
                    newRecyclerView.adapter = adapter
                    val cours = findViewById<View>(R.id.cours) as TextView
                    val nbreCours = adapter.itemCount
                    cours.setText("Cours (" +nbreCours+ ")")
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}