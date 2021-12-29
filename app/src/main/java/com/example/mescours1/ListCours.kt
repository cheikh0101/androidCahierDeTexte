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
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.models.CourX

class ListCours (private val newList: ArrayList<CourX>):
    RecyclerView.Adapter<ListCours.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val vHoraire: TextView = itemView.findViewById(R.id.vHoraire)
        val ecIntitule: TextView = itemView.findViewById(R.id.ecIntitule)
        val nomSemestre: TextView = itemView.findViewById(R.id.nomSemestre)
        val details: Button = itemView.findViewById(R.id.details)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.liste_cours,parent,false)
        return MyViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n", "CommitPrefEdits")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.ecIntitule.text = currentItem.ecIntitule
        holder.vHoraire.text = currentItem.vHoraire.toString() + 'H'
        holder.nomSemestre.text = currentItem.nomSemestre
        holder.details.setOnClickListener {
            val intent = Intent(holder.itemView.context, ActionDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)

            val sharedPreferences: SharedPreferences = holder.itemView.context.getSharedPreferences("actions_cours", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.apply{ putString("ecIntitule", holder.ecIntitule.text as String)
            }.apply()
        }

    }

    override fun getItemCount(): Int {
        return newList.size
    }
}