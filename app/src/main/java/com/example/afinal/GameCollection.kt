package com.example.afinal

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project.Node
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class GameCollection : AppCompatActivity() {

    private lateinit  var _rvCollection : RecyclerView
    private fun TampilkanData(){
        _rvCollection.layoutManager = LinearLayoutManager(this)

        //inisialisasi db
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()
        sp =  getSharedPreferences("dataSP", MODE_PRIVATE)

        val arrCollection = mutableListOf<Int>()
//        db.collection("tbCollectionUser").get()
//            .addOnSuccessListener { result ->
//                for(document in result){
//                    if(document.data.get("userID").toString() == sp.getString("userID", null)){
//                        arrCollection.add(document.data.get("collectionID").toString().toInt())
//                    }
//                }
//            }

        db.collection("tbCollectionUser").whereEqualTo("userID",sp.getString("userID", null)).orderBy("collectionID", Query.Direction.ASCENDING).get()
            .addOnSuccessListener { result ->
                for(document in result){
                    println(document.data.get("collectionID"))
                    arrCollection.add(document.data["collectionID"].toString().toInt())
                }
            }
        val adapterP = adapterColletion(arrCollection)
        _rvCollection.adapter = adapterP
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_collection)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        _rvCollection = findViewById(R.id.rvCollection)
        TampilkanData()
    }
}