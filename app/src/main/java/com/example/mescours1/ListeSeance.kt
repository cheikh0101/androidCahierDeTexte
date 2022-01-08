package com.example.mescours1

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.api.ApiClient
import com.example.mescours1.models.SeanceX
import kotlinx.coroutines.*
import retrofit2.HttpException

class ListeSeance(private val newList: ArrayList<SeanceX>): RecyclerView.Adapter<ListeSeance.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titre: TextView = itemView.findViewById(R.id.titre)
        val description: TextView = itemView.findViewById(R.id.description)
        val remarque: TextView = itemView.findViewById(R.id.remarque)
        val nature: TextView = itemView.findViewById(R.id.nature)
        val duree: TextView = itemView.findViewById(R.id.duree)
        val suppression: Button = itemView.findViewById(R.id.suppression)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_liste_seance,parent,false)
        return ListeSeance.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.titre.text = "Titre: " + currentItem.titre
        holder.description.text = "Description: " + currentItem.description
        holder.remarque.text = "Remarque: " + currentItem.remarque
        holder.nature.text = "Nature: " + currentItem.nature
        holder.duree.text = "Durée: " + currentItem.duree
        holder.suppression.setOnClickListener {
            val service = ApiClient.makeRetrofitService()
            CoroutineScope(Dispatchers.IO).launch {
                val response = service.deleteSeance(currentItem.id);
                withContext(Dispatchers.Main) {
                    try {
                        println("seance supprimee avec succes")
                    } catch (e: HttpException) {
                        println("Exception ${e.message}")
                    } catch (e: Throwable) {
                        println("Ooops: Something else went wrong")
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}