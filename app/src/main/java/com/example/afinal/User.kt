package com.example.afinal
//@Entity
data class User (
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name= "user_id")
//    var id: Int = 0,

    var email: String? = null,
//    @ColumnInfo(name = "username")
    var username: String? = null,

//    @ColumnInfo(name = "password")
    var password: String? = null,

//    @ColumnInfo(name = "score")
    var score: Int? = null


)
