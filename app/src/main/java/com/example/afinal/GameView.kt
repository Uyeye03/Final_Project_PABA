package com.example.afinal

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.afinal.MainActivity.Companion.bst
import com.example.project.BST
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.NonCancellable.cancel

class GameView : AppCompatActivity() {
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_view)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        //inisialisasi db
        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        //inisialisasi xml
        val btn_left = findViewById<Button>(R.id.btn_left)
        val btn_right = findViewById<Button>(R.id.btn_right)
        val tv_main = findViewById<TextView>(R.id.tv_main)
        var tv_timer = findViewById<TextView>(R.id.tv_timer)
        val tv_score = findViewById<TextView>(R.id.tv_score)

        val gv = findViewById<ConstraintLayout>(R.id.game_view)

        //game variable
        var count = 16
        var gameOver = false
        var score : Int = 0
        var size = 0
        var cekCollection= false
        var userChoice = mutableListOf<String>()
        db.collection("tbHistory").document()
        db.collection("tbCollectionUser").document()

        //jalannya count dan pengecekan
       object: CountDownTimer(86400000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                count--
                //tv_timer.setText("Time remaining: " + millisUntilFinished / 1000)
                if (count>=0){
                tv_timer.setText("Time remaining: " + count)
                }else{
                    gameOver = true
                    btn_right.setText("MAIN MENU")
                    btn_left.setText("TRY AGAIN")
                    tv_timer.setText("")
                    tv_main.setText("You too much think and dead because of it")

                    gv.background = resources.getDrawable(R.drawable.die_screen)
                }

            }

            override fun onFinish() {
                tv_timer.setText("0")
            }
        }.start()






     println(bst.root!!.text)
     println(bst.root!!.left!!.text)
     println(bst.root!!.right!!.text)
     
        var nowNode = bst.root!!
        tv_main.setText("One day, you want to go hiking\nBut on the way, your car got stuck in deep woods\nWhat would you do?")
        val uriPath = "android.resource://$packageName/raw/${nowNode.utility[1]}"
        val uri: Uri = Uri.parse(uriPath)
        mediaPlayer = MediaPlayer.create(this, uri)
        try {
         mediaPlayer?.start()
        } catch (e: Exception) {

        }

        btn_right.setText(nowNode!!.choice[1])
        btn_left.setText(nowNode!!.choice[0])

        println("Timer di luar :"+count)



        btn_right.setOnClickListener {

            //save choice user

//            println(count)

            mediaPlayer.stop()
            if (count<=0 || gameOver || nowNode.right == null){ //cek kalau pilih main menu

                val eIntent = Intent(this@GameView,MainActivity::class.java)
                eIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(eIntent)
            }
            else if(nowNode.right != null){
                userChoice.add(nowNode!!.choice[1]!!)
                nowNode = nowNode.right!!
                score += 100 + count
                if(nowNode.choice.size > 1){ //cek blm ending
                    count =16
                }else{
                    //kalau sudah ending
//                    timer.cancel();

                    sp =  getSharedPreferences("dataSP", MODE_PRIVATE)
                    val user = sp.getString("userID", null)

                    //save highscore
                    if( sp.getInt("userScore", 0) < score){
                        val editor = sp.edit()
                        editor.putInt("userScore", score)
                        editor.apply()

                        db.collection("tbUser").document(user!!)
                            .update("score", score)
                    }

                    //save collection

                    db.collection("tbCollectionUser").whereEqualTo("userID", user).get()
                        .addOnSuccessListener { result ->
                            size = result.size()
//                            println(size)
                            if(size > 0) {
                                for (document in result) {
                                    println("ini boolean")
                                    println(document.data.get("collectionID").toString().toInt())
                                    println (nowNode.value)
                                    if (document.data.get("collectionID").toString().toInt() == nowNode.value) {
                                        cekCollection = true
                                        break
                                    }
                                }

                                if(!cekCollection){
                                    db.collection("tbCollectionUser")
                                        .document((size + 1).toString())
                                        .set(
                                            userCollection(
                                                size+1,
                                                sp.getString("userID", null),
                                                nowNode.value
                                            )
                                        )
                                    Toast.makeText(
                                        this@GameView,
                                        "Unlocked New Collection!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                            }else{
                                db.collection("tbCollectionUser")
                                    .document((size + 1).toString())
                                    .set(
                                        userCollection(
                                            size+1,
                                            sp.getString("userID", null),
                                            nowNode.value
                                        )
                                    )
                                Toast.makeText(
                                    this@GameView,
                                    "Unlocked New Collection!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }

                    //save choice user
                    db.collection("tbHistory").get()
                        .addOnSuccessListener { result ->
                            size = result.size()
//                            println(size)

                            db.collection("tbHistory").document((size+1).toString())
                                .set(historyUser(size+1, sp.getString("userID", null), userChoice))
                        }
                }
//                println(score)
            }


            var temp_text = ""

            for(textData in nowNode.text){
                temp_text += textData + "\n"
//                Timer().schedule(1000) {
                tv_main.setText(temp_text)
//                }
            }
            if(nowNode.choice.size > 1){
                btn_right.setText(nowNode!!.choice[1])
                btn_left.setText(nowNode!!.choice[0])
                var imageRes = resources.getDrawable(this.resources.getIdentifier(nowNode.utility[0], "drawable", this.packageName), null)
                gv.background = imageRes

                val uriPath = "android.resource://$packageName/raw/${nowNode.utility[1]}"
                val uri: Uri = Uri.parse(uriPath)
                mediaPlayer = MediaPlayer.create(this, uri)
                try {
                    mediaPlayer?.start()
                } catch (e: Exception) {

                }
            }else{

                btn_right.setText("MAIN MENU")
                btn_left.setText("TRY AGAIN")
                tv_score.setText("Your Score: " + score)
//                var imageRes = resources.getDrawable(this.resources.getIdentifier(nowNode.utility[0], "drawable", this.packageName), null)
//                gv.background = imageRes
//                val uriPath = "android.resource://$packageName/raw/${nowNode.utility[1]}"
//                val uri: Uri = Uri.parse(uriPath)
//                mediaPlayer = MediaPlayer.create(this, uri)
//                try {
//                    mediaPlayer?.start()
//                } catch (e: Exception) {
//
//                }
            }

        }
        println(bst.search(61)!!.utility[0])
        btn_left.setOnClickListener {


         //matiin sound effect
         mediaPlayer.stop()
            println(count)

            if (gameOver || nowNode.left == null){ //cek kalau pilih try again

               nowNode = bst.root!!
               count = 16
//               println(count)
               score = 0
               tv_score.setText("")
               gameOver = false
            }else if(nowNode.left != null) { //cek kalau belum gameover
                //save user choice in array
                userChoice.add(nowNode!!.choice[1]!!)

                nowNode = nowNode.left!!
                score += 100 + count
                if(nowNode.choice.size > 1){ //pengecekan sudah di ending / belum
                 count =16 //kalau belum, reset timer
                }else{
                 //kalau sudah , disable timer
//                       timer.cancel();
                    sp =  getSharedPreferences("dataSP", MODE_PRIVATE)

                    //save highScore
                    if(sp.getInt("userScore", 0) < score){
                        val editor = sp.edit()
                        editor.putInt("userScore", score)
                        editor.apply()

                        db.collection("tbUser").document(sp.getString("userID", null)!!)
                            .update("score", score)
                    }

                    //save collection
                    db.collection("tbCollectionUser").whereEqualTo("userID", sp.getString("userID", null)).get()
                        .addOnSuccessListener { result ->
                            size = result.size()
//                            println(size)
                            if(size > 0) {
                                for (document in result) {
                                    println(
                                        (document.data.get("collectionID").toString()
                                            .toInt() != nowNode.value)
                                    )
                                    println(
                                        document.data.get("userID")
                                            .toString() != sp.getString("userID", null)
                                    )
                                    if (document.data.get("collectionID").toString().toInt() == nowNode.value) {
                                        cekCollection = true
                                        break
                                    }
                                }

                                if(!cekCollection){
                                    db.collection("tbCollectionUser")
                                        .document((size + 1).toString())
                                        .set(
                                            userCollection(
                                                size+1,
                                                sp.getString("userID", null),
                                                nowNode.value
                                            )
                                        )
                                    Toast.makeText(
                                        this@GameView,
                                        "Unlocked New Collection!",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            }else{
                                db.collection("tbCollectionUser")
                                    .document((size + 1).toString())
                                    .set(
                                        userCollection(
                                            size+1,
                                            sp.getString("userID", null),
                                            nowNode.value
                                        )
                                    )
                                Toast.makeText(
                                    this@GameView,
                                    "Unlocked New Collection!",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }

                    //save history choice user
                    db.collection("tbHistory").get()
                        .addOnSuccessListener { result ->
                            size = result.size()
                            println(size)

                            db.collection("tbHistory").document((size+1).toString())
                                .set(historyUser(size+1, sp.getString("userID", null), userChoice))
                        }

                }
//                println(score)
            }


            var temp_text = ""
            for(textData in nowNode.text){
                temp_text += textData + "\n"
//                Timer().schedule(1000) {
                tv_main.setText(temp_text)
//                }
            }

            if(nowNode.choice.size > 1){ //cek game over, buat set button
                btn_right.setText(nowNode!!.choice[1])
                btn_left.setText(nowNode!!.choice[0])
                var imageRes = resources.getDrawable(this.resources.getIdentifier(nowNode.utility[0], "drawable", this.packageName), null)
                gv.background = imageRes

                val uriPath = "android.resource://$packageName/raw/${nowNode.utility[1]}"
                val uri: Uri = Uri.parse(uriPath)
                mediaPlayer = MediaPlayer.create(this, uri)
                try {
                    mediaPlayer?.start()
                } catch (e: Exception) {

                }
            }else {
             btn_right.setText("MAIN MENU")
             btn_left.setText("TRY AGAIN")
//                var imageRes = resources.getDrawable(this.resources.getIdentifier(nowNode.utility[0], "drawable", this.packageName), null)
//                gv.background = imageRes
//                val uriPath = "android.resource://$packageName/raw/${nowNode.utility[1]}"
//                val uri: Uri = Uri.parse(uriPath)
//                 mediaPlayer = MediaPlayer.create(this, uri)
//                try {
//                    mediaPlayer?.start()
//                } catch (e: Exception) {
//
//                }
            }

        }

    }
}