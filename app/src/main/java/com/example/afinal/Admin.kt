package com.example.project

import java.util.*
import kotlin.collections.ArrayList

//value = index destinasi ini
//utility = object yang perlu dirender
//text = kalimat yang diutarakan pada game
//choice = pilihan yang akan dilakukan
//nextVal = pilihan destinasi selanjutnya
class Node(var value: Int,
           var utility: MutableList<String>,
           var text: MutableList<String>,
           var choice: MutableList<String?>,
           var nextVal: MutableList<Int?>){
    var data = value
    var tulisan = text
    var util = utility
    var pilihan = choice
    var nextValue = nextVal
    lateinit var dataGame: MutableList<MutableList<String>>
//    var dataGame: ArrayList<ArrayList<String>> = arrayListOf(arrayListOf())
    var left: Node? = null
    var right: Node? = null
}

//Memory Overusage
//class BST constructor(var root: Node? = null){
//    fun insert(value: Int, utility: MutableList<String>, text: MutableList<String>, choice: MutableList<String?>, nextVal: MutableList<Int?>){

//
//        var util_temp: MutableList<String> = mutableListOf()
//        for (i in utility){
//            util_temp.add(i)
//        }
//
//        var text_temp: MutableList<String> = mutableListOf()
//        for (i in text){
//            text_temp.add(i)
//        }
//        var choice_temp: MutableList<String?> = mutableListOf()
//        for (i in choice){
//            choice_temp.add(i)
//        }
//        var nextVal_temp: MutableList<Int?> = mutableListOf()
//        for (i in nextVal){
//            nextVal_temp.add(i)
//        }
//
//
//
//        fun DFS(current: Node?, value: Int, utility: MutableList<String>, text: MutableList<String>, choice: MutableList<String?>, nextVal: MutableList<Int?>): Node {
//            current ?: return Node(value, utility, text, choice, nextVal)
//
//
//
//            if (value < current.value){
//
////                current.left = DFS(current.left, value, utility, text, choice, nextVal)
//                current.left = DFS(current.left, value, util_temp, text_temp, choice_temp, nextVal_temp)
//            }
//            else if (value > current.value){
//                //current.right = DFS(current.right, value, utility, text, choice, nextVal)
//                current.right = DFS(current.right, value, util_temp, text_temp, choice_temp, nextVal_temp)
//            }
//            return current
//        }
//        if(this.root == null){
//            //this.root = Node(value, utility, text, choice, nextVal)
//            this.root = Node(value, util_temp, text_temp, choice_temp, nextVal_temp)
//        }
//        else{
//            //this.root = DFS(this.root, value, utility, text, choice, nextVal)
//            this.root = DFS(this.root, value, util_temp, text_temp, choice_temp, nextVal_temp)
//        }
//    }

// Memory Efficient
class BST constructor(var root: Node? = null){
    fun insert(value: Int, utility: MutableList<String>, text: MutableList<String>, choice: MutableList<String?>, nextVal: MutableList<Int?>){
        fun DFS(current: Node?, value: Int, utility: MutableList<String>, text: MutableList<String>, choice: MutableList<String?>, nextVal: MutableList<Int?>): Node {
            current ?: return Node(value, utility, text, choice, nextVal)

            if (value < current.value){
                current.left = DFS(current.left, value, utility, text, choice, nextVal)
//                println(util_temp)
            }
            else if (value > current.value){
                current.right = DFS(current.right, value, utility, text, choice, nextVal)
//                println(util_temp)
            }
            return current
        }
        if(this.root == null){
            this.root = Node(value, utility, text, choice, nextVal)
        }
        else{
            this.root = DFS(this.root!!, value, utility, text, choice, nextVal)
        }
    }

    fun printInorder(){
        fun DFS(current: Node?){
            current ?: return
            DFS(current.left)
            println(current.value)
            DFS(current.right)
        }
        DFS(this.root)
        println("")
    }

    fun search(value: Int): Node? {
        fun DFS(current: Node?, value: Int): Node? {
            //Base Condition
            if (value < current!!.value){
                 return DFS(current.left, value)
            }
            else if (value > current.value){
                 return DFS(current.right, value)
            }
            else{
                return current
            }
        }
        return DFS(this.root, value)
    }

}



