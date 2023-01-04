package com.example.afinal

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.*


class HighScore : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_high_score)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        var score = mutableListOf<Int>()
        var name = mutableListOf<String>()


        val tl = findViewById<TableLayout>(R.id.tl_highScore)

        db.collection("tbUser").orderBy("score", Query.Direction.DESCENDING).limit(3).get()
            .addOnSuccessListener { result ->
                for(document in result){
                    score.add(document.data.get("score").toString().toInt())
                    name.add(document.data.get("username").toString())

                }
                 addRow(name, score, tl)
            }
            .addOnFailureListener{
                Log.d("Firebase", it.message.toString())
            }

    }

        fun addRow( name: MutableList<String>, score: MutableList<Int>, tl: TableLayout){
            println(name.size)
            for (i in 0..score.size-1) {
                println("hey")
                val row = TableRow(this)
                val lp = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT)
                var name = name[i]
                var score = score[i]
                val params =
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.setMargins(16, 16, 16, 16)

                row.layoutParams = lp

                //initiate textview
                var rank = TextView(this)
                var tvname = TextView(this)
                var tvscore = TextView(this)

                tvname.setGravity(Gravity.NO_GRAVITY)
                rank.setGravity(Gravity.LEFT);
                tvscore.setGravity(Gravity.CENTER_HORIZONTAL);

                //fill in textview
                rank.text = "${i+1}."
                tvscore.setText(score.toString())
                tvname.text = name
//
//                rank.layoutParams = params
//                tvname.layoutParams = params
//                tvscore.layoutParams = params
                //append to row
                row.addView(rank)
                row.addView(tvname)
                row.addView(tvscore)

                //append to table
                tl.addView(row, i)
        }
    }
}