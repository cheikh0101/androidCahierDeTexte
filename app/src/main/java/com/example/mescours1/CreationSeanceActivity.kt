package com.example.mescours1

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.Seance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import android.widget.TimePicker
import androidx.annotation.RequiresApi


class CreationSeanceActivity : AppCompatActivity() {
    lateinit var nature: AutoCompleteTextView
    lateinit var titre: TextView;
    lateinit var description: TextView
    lateinit var remarque: TextView
    lateinit var duree: TimePicker
    lateinit var button: Button;
    lateinit var x: String
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creation_seance)
        val items = listOf("Cours Magistral", "TD", "TP", "Devoir", "Examen", "Autres")
        val adapter = ArrayAdapter(this, R.layout.list_item, items)
        nature = findViewById(R.id.nature)
        nature.setAdapter(adapter)
        titre = findViewById(R.id.titre)
        description = findViewById(R.id.description)
        remarque = findViewById(R.id.remarque)
        duree = findViewById(R.id.duree)
        val sharedPreferences1 = this.getSharedPreferences("user_information", Context.MODE_PRIVATE)
        val users_id = sharedPreferences1.getInt("user_id", 3)
        val sharedPreferences = this.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
        val cours_id = sharedPreferences.getInt("id", 2)
        button = findViewById(R.id.button)
        button.setOnClickListener{
            if (titre.text.toString() == "" || description.text.toString() == "" ){
                Toast.makeText(
                    this,
                    "Champs obligatoires ",
                    Toast.LENGTH_LONG
                ).show()
            }
            else{
                x = duree.hour.toString() + "h : " + duree.minute.toString() + " mn"
                this.seanceCreate(Seance(nature.text.toString(), titre.text.toString(), description.text.toString(), remarque.text.toString(), x, users_id, cours_id ))
            }
        }
    }
    private fun seanceCreate(seance: Seance) {
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.seanceCreate(seance);
            withContext(Dispatchers.Main) {
                try {
                    val intent = Intent(this@CreationSeanceActivity, MesSeancesActivity::class.java)
                    startActivity(intent)
                    finish()
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}