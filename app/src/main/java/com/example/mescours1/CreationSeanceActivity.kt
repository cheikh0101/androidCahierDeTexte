package com.example.mescours1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.Seance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class CreationSeanceActivity : AppCompatActivity() {
    lateinit var nature: AutoCompleteTextView
    lateinit var titre: TextView;
    lateinit var description: TextView
    lateinit var remarque: TextView
    lateinit var duree: TextView
    lateinit var button: Button;
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
        val users_id = sharedPreferences1.getInt("users_id", 3)

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
                this.seanceCreate(Seance(nature.text.toString(), titre.text.toString(), description.text.toString(), remarque.text.toString(), duree.text.toString(), users_id, cours_id ))
            }
        }
    }

    private fun seanceCreate(seance: Seance) {
        val service = ApiClient.makeRetrofitService()
        CoroutineScope(Dispatchers.IO).launch {
            val response = service.seanceCreate(seance);
            withContext(Dispatchers.Main) {
                try {
                    println(response)
                } catch (e: HttpException) {
                    println("Exception ${e.message}")
                } catch (e: Throwable) {
                    println("Ooops: Something else went wrong")
                }
            }
        }
    }
}