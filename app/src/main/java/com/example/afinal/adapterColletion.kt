package com.example.afinal

import android.media.Image
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.afinal.MainActivity.Companion.bst
import com.example.project.BST
import com.example.project.Node

class adapterColletion(
    private val gambarCollection: MutableList<Int>
): RecyclerView.Adapter<adapterColletion.ListViewHolder>() {
    var arrayEnding = mutableListOf<Int>(9, 19, 23, 27, 31, 53, 61, 41, 81, 101, 111, 106, 108, 114, 116, 118, 120, 123, 127)
    private lateinit var onItemClickCallback : OnItemClickCallback
    inner class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var _gambarCollection : ImageView = itemView.findViewById(R.id.gambar)
        var _idCollection : TextView = itemView.findViewById(R.id.seriesnum)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun setCollectionData(pos: Node)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view : View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
//        val context = holder.itemView.context
//        val imageRes = context.resources.getIdentifier(GameView.rot!!.utility[0], "drawable", context.packageName)

        var cekCollection = arrayEnding[position]
        if(gambarCollection.contains(cekCollection)){
            val context = holder.itemView.context
            val imageRes = context.resources.getIdentifier(bst.search(cekCollection)!!.utility[0], "drawable", context.packageName)
            holder._gambarCollection.setImageResource(imageRes)
            holder._idCollection.setText(cekCollection.toString())
        }else{
            holder._gambarCollection.setImageResource(R.drawable.question)
            holder._idCollection.setText("???")
        }

    }

    override fun getItemCount(): Int {
        return 19
    }

}