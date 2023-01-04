package com.example.afinal

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson

lateinit var sp : SharedPreferences;
class LoginPage : AppCompatActivity() {
    var db : FirebaseFirestore = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)

        //set orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        var isUser = false;
        val _tvRegister = findViewById<TextView>(R.id.tvRegister)
        _tvRegister.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
        val _btnLogin = findViewById<Button>(R.id.loginBtn)
        val _etUser = findViewById<EditText>(R.id.etUser)
        val _etPass = findViewById<EditText>(R.id.etPass)

        fun readData (db : FirebaseFirestore){
            db.collection("tbUser").get()
                .addOnSuccessListener { result ->
                    for(document in result){
                        val dataUser = User(document.data.get("email").toString(), document.data.get("username").toString(), document.data.get("password").toString(), document.data.get("score").toString().toInt())
                        if (_etUser.text.toString() == dataUser.username && _etPass.text.toString() == dataUser.password|| _etUser.text.toString() == dataUser.email && _etPass.text.toString() == dataUser.password){
                            isUser = true
                            sp = getSharedPreferences("dataSP", MODE_PRIVATE)

                            val editor = sp.edit()
                            editor.putString("userName", dataUser.username)
                            editor.putString("userID", dataUser.email)
                            editor.putInt("userScore", dataUser.score!!)
                            editor.apply()

                            println(sp.getString("userName", null))
                            println(sp.getString("userID", null))

                            println(isUser)
                            var sendIntent = Intent(this@LoginPage, MainActivity::class.java)
                            startActivity(sendIntent)
                        }
                    }
                }
                .addOnFailureListener{
                    Log.d("Firebase", it.message.toString())
                }
        }
        _btnLogin.setOnClickListener {
            readData(db)
        }
        _tvRegister.setOnClickListener{
            val registIntent = Intent(this@LoginPage, RegisterPage::class.java)
            startActivity(registIntent)
        }
    }
}