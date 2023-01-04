package com.example.afinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class adapterChoice(
    private val choice : MutableList<MutableList<String>>
): RecyclerView.Adapter<adapterChoice.ListViewHolder>(){
    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var _gameNo : TextView = itemView.findViewById(R.id.noGame)
        var tvChoice : TextView = itemView.findViewById(R.id.tvChoice)
    }


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun setData()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): adapterChoice.ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_choicerecycler, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {

        var choiceData = choice[position]
        var textChoice = ""
        for (data in choiceData){
            textChoice+= data + "\n"
        }

        holder._gameNo.setText("GAME " + (position+1))
        holder.tvChoice.setText(textChoice)

    }


    override fun getItemCount(): Int {
        return choice.size
    }
}