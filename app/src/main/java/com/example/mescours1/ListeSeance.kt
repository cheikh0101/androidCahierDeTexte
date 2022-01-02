package com.example.mescours1

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mescours1.models.SeanceX

class ListeSeance(private val newList: ArrayList<SeanceX>): RecyclerView.Adapter<ListeSeance.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titre: TextView = itemView.findViewById(R.id.titre)
        val description: TextView = itemView.findViewById(R.id.description)
        val remarque: TextView = itemView.findViewById(R.id.remarque)
        val nature: TextView = itemView.findViewById(R.id.nature)
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
    }

    override fun getItemCount(): Int {
        return newList.size
    }
}