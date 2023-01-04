package com.example.afinal

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class ChoiceHistory : AppCompatActivity() {
    private lateinit  var _rvChoice : RecyclerView

    private fun TampilkanData(){
        _rvChoice.layoutManager = LinearLayoutManager(this)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val choice = mutableListOf<MutableList<String>>()
        db.collection("tbHistory").get()
            .addOnSuccessListener {
                    result ->
                var size = result.size()
                for(document in result){
                    if(document.data.get("userID").toString() == sp.getString("userID", null)){
                        val listchoice = mutableListOf<String>()
                        for (data in document.data.get("choice") as List<*>){
                            listchoice.add(data.toString())
                        }
                        choice.add(listchoice)
//                        println(choice)
                    }
                }
//                println(choice)
                val adapterP = adapterChoice(choice)
                _rvChoice.adapter = adapterP

            }



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice_history)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        _rvChoice = findViewById(R.id.rvHistory)
        TampilkanData()



    }
}