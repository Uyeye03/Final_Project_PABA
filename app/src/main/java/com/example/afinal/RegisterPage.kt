package com.example.afinal

import android.content.Intent
import android.content.pm.ActivityInfo
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async


class RegisterPage : AppCompatActivity() {
    lateinit var db : FirebaseFirestore
    lateinit var _etUser : EditText
    lateinit var _etEmail : EditText
    lateinit var _etPass : EditText
    lateinit var _etConfPass : EditText
    var uid : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)

        //set orientation
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        db = FirebaseFirestore.getInstance()
        _etUser = findViewById<EditText>(R.id.etUser)
        _etEmail = findViewById(R.id.etEmail)
        _etPass = findViewById<EditText>(R.id.etPass)
        _etConfPass = findViewById<EditText>(R.id.etConfPass)
        val  _notif = findViewById<TextView>(R.id.notif);
        val _registerBtn = findViewById<Button>(R.id.registerBtn)
        val _tvLogin = findViewById<TextView>(R.id.tvLogin)
//        _registerBtn.isEnabled = false
//        _registerBtn.isClickable = false

        println(_registerBtn.isEnabled)
        _tvLogin.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)
        _tvLogin.setOnClickListener{
            val registIntent = Intent(this@RegisterPage, MainActivity::class.java)
            startActivity(registIntent)
        }

        _etConfPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                println(_etConfPass.text.toString())
                if(_etPass.text.toString() != _etConfPass.text.toString()){
                    _notif.setText("Password does not match")
                    _notif.setTextColor(Color.RED)
                    _registerBtn.isEnabled = false
                }else{
                    _notif.setTextColor((Color.parseColor("#32a84c")))
                    _notif.setText("Password Confirmed")
                    _registerBtn.isEnabled = true
                }
            }

            override fun afterTextChanged(s: Editable) {}
        })


        fun addAccount(db: FirebaseFirestore, user: User) {

            db.collection("tbUser").document(user.email.toString())
                .set(user)
                .addOnSuccessListener {
                    Log.d("Firebase", "Simpan Data Berhasil")
                    val registIntent = Intent(this@RegisterPage, MainActivity::class.java)
                    startActivity(registIntent)
                }
                .addOnFailureListener {
                    Log.d("Firebase", it.message.toString())
                }
        }

        _registerBtn.setOnClickListener {
            if(_etUser.text.toString() == "" || _etEmail.text.toString() == "" ){
                Toast.makeText(this@RegisterPage, "Data incomplete", Toast.LENGTH_LONG).show()
            }else{
                val newUser = User(_etEmail.text.toString(), _etUser.text.toString(), _etPass.text.toString(), 0)
                addAccount(db, newUser)
            }

        }
    }
}