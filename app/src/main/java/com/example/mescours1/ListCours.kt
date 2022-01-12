package com.example.mescours1

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.models.CourX

class ListCours (private val newList: ArrayList<CourX>):
    RecyclerView.Adapter<ListCours.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val vHoraire: TextView = itemView.findViewById(R.id.vHoraire)
        val ecIntitule: TextView = itemView.findViewById(R.id.ecIntitule)
        //val nomSemestre: TextView = itemView.findViewById(R.id.nomSemestre)
        val details: Button = itemView.findViewById(R.id.details)
        val nomNiveau: TextView = itemView.findViewById(R.id.nomNiveau)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.liste_cours,parent,false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.ecIntitule.text = currentItem.ecIntitule
        if(currentItem.nomSemestre == "semestre 1" || currentItem.nomSemestre == "semestre 2") {
            holder.nomNiveau.setText("Niveau: Licence 1")
        }else if(currentItem.nomSemestre == "semestre 3" || currentItem.nomSemestre == "semestre 4"){
            holder.nomNiveau.setText("Niveau: Licence 2")
        }else if(currentItem.nomSemestre == "semestre 5" || currentItem.nomSemestre == "semestre 6"){
            holder.nomNiveau.setText("Niveau: Licence 3")
        }
        holder.details.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActionDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
            val sharedPreferences: SharedPreferences = holder.itemView.context.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{
                putString("vHoraire", currentItem.vHoraire.toString())
                putString("ecIntitule", holder.ecIntitule.text as String)
                putString("nomSemestre", currentItem.nomSemestre)
                putString("ueIntitule", currentItem.ueIntitule)
                putInt("id",currentItem.id)
            }.apply()
        }
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}