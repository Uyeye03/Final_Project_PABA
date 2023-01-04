package com.example.afinal

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.ActivityInfo
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.project.BST


class MainActivity : AppCompatActivity() {
    companion object{
        lateinit var bst : BST
    }
    lateinit var sp : SharedPreferences
    lateinit var mediaPlayer: MediaPlayer
    fun initiateTree(){
        //Inisialisasi Tree
        bst = BST()
        var util: MutableList<String> = mutableListOf()
        var text: MutableList<String> = mutableListOf()
        var choice : MutableList<String?> = mutableListOf()
        var nextValue : MutableList<Int?> = mutableListOf()

//      1. Game Root / Awal cerita / #65
        var temp: MutableList<String> = mutableListOf()
        var temp2 :  MutableList<String> = mutableListOf()
        var temp3 :  MutableList<String?> = mutableListOf()
        var temp4 : MutableList<Int?> = mutableListOf()

        //1. Game Root / Awal cerita / //65
        util = mutableListOf("car_crash_bmw", "car_crash_skid")
        text = mutableListOf("One day, you want to go hiking", "But on the way, your car got stuck in deep woods", "What would you do?")
        choice = mutableListOf("Wait in car", "Find some help")
        nextValue = mutableListOf(33, 97)
        bst.insert(65, util, text, choice, nextValue)

        //FULL KANAN!
        //Path 97 (kanan)
        util = mutableListOf("a97_forest_path_bmw", "a97_animal_sound")
        text = mutableListOf("You choice to find some help", "You go deep into the woods", "Suddenly, you hear some scary sound", "What would you do?")
        choice = mutableListOf("Lari ke kiri", "Lari ke kanan")
        nextValue = mutableListOf(81, 113)
        bst.insert(97, util, text, choice, nextValue)

        //Path DIE 81 (kiri)
        util = mutableListOf("a81_tiger", "dead")
        text = mutableListOf("You died, some hungry tiger suddenly appeared before you", "He jump straight into your body, as he seen a weak and meaty body of yours.")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(81, util, text, choice, nextValue)

        //Path 113 (kanan)
        util = mutableListOf("a113", "silent")
        text = mutableListOf("You saw some strange symbol hinting to go right", "It maybe a good sign, or a bad sign..")
        choice = mutableListOf("Go opposite way", "Follow the symbol")
        nextValue = mutableListOf(105, 121)
        bst.insert(113, util, text, choice, nextValue)

        //Path 105 (kiri)
        util = mutableListOf("a105", "silent")
        text = mutableListOf("You ran to the opposite way from the symbol", "You see some light from afar", "It seems some local villagers may want to meet you..")
        choice = mutableListOf("Ran Away!!", "Meet the villagers")
        nextValue = mutableListOf(101, 109)
        bst.insert(105, util, text, choice, nextValue)

        //Path 121 (kanan)
        util = mutableListOf("a121", "search")
        text = mutableListOf("It seems that symbol made by the last survivor.", "He/ She left you a flashlight and a map", "The notes tells you where to get the map and some clues to get it")
        choice = mutableListOf("Follow the notes", "Ignore the notes")
        nextValue = mutableListOf(117, 125)
        bst.insert(121, util, text, choice, nextValue)

        //Path 117 (kiri)
        util = mutableListOf("a117", "a17_foot_step")
        text = mutableListOf("The notes tells you the map located in the Canibal\"s house", "You are in front the door of the canibal\"s house")
        choice = mutableListOf("Kill the canibal", "Come inside sneakily")
        nextValue = mutableListOf(115, 119)
        bst.insert(117, util, text, choice, nextValue)

        //Path 125 (kanan)
        util = mutableListOf("a125", "a17_foot_step")
        text = mutableListOf("You keep walking and start entering the fog", "After some time, you see some silhouette.. It looks like some people standing")
        choice = mutableListOf("Approach them", "Go back")
        nextValue = mutableListOf(123, 127)
        bst.insert(125, util, text, choice, nextValue)

        //Path 123 (kiri)
        util = mutableListOf("a127_123", "a123_127_zombie_eat")
        text = mutableListOf("WHOA! It\"s a DAMN.. ZOMBIE.. APOCALYPSE..", "They surrounded you and have a banquet on you", "Only your bones remained..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(123, util, text, choice, nextValue)

        //Path 127 (kanan)
        util = mutableListOf("a127_123", "a123_127_zombie_eat")
        text = mutableListOf("You tried to run", "But a rock tripped your feet and they quickly eat every part of you", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(127, util, text, choice, nextValue)

        //Path 115 (kiri)
        util = mutableListOf("a115", "search")
        text = mutableListOf("You collected your thoughts and planned to kill the canibal!", "The notes tells you the canibal set his own traps around his own house..", "You make use of that and killed the canibal!", "The only thing left is to search for the map..")
        choice = mutableListOf("search Bedroom", "search the canibal\"s corpse")
        nextValue = mutableListOf(114, 116)
        bst.insert(115, util, text, choice, nextValue)

        //Path 119 (kanan)
        util = mutableListOf("a119", "running_2")
        text = mutableListOf("After doing some long consideration, you choose to sneak inside his house..", "But you immediately trigerred the canibal\"s traps!", "The canibal knows you are inside his house and chases you!", "After running through a corridor, you see two doors")
        choice = mutableListOf("Go in the kitchen", "Hide in the Bedroom")
        nextValue = mutableListOf(118, 120)
        bst.insert(119, util, text, choice, nextValue)

        //Path 114 (kiri)
        util = mutableListOf("die_screen", "a114_shotgun")
        text = mutableListOf("You tried to search the canibal\"s bedroom.", "Unfortunately.. while searching the map", "Your foot slipped and triggerred his last trap..", "Causing you to be shot by a rifle in your face..", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(114, util, text, choice, nextValue)

        //Path 116 (kanan)
        util = mutableListOf("a23", "silent")
        text = mutableListOf("You decided to search for the corpse first", "And the map was hidden in his inner most pocket", "You found the map and with the notes.., you managed to survive in the forest", "You searched around the house and found a galon of gas", "You flee from the forest in the morning")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(116, util, text, choice, nextValue)

        //Path 118 (kiri)
        util = mutableListOf("a118", "sad_end")
        text = mutableListOf("You go in the kitchen to find a weapon", "You found a kitchen knife", "You used it to stab the canibal, But..", "It turns out to be a replica knife", "The canibal caught you..", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(118, util, text, choice, nextValue)

        //Path 120 (kanan)
        util = mutableListOf("die_screen", "a120_explosion")
        text = mutableListOf("You choose to hide inside his bedroom", "Unfortunately, your foot steps on the wrong plank and triggered a trap", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(120, util, text, choice, nextValue)

        //Path 101 (kiri)
        util = mutableListOf("a101", "a101_animal_trap")
        text = mutableListOf("You are getting scared and decided to run away", "But your feet get caught in an animal trap", "The villagers think you are a wild animal and hunted you..", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(101, util, text, choice, nextValue)

        //Path 109 (kanan)
        util = mutableListOf("a109", "a17_foot_step")
        text = mutableListOf("You muster up your courage and meet the villagers", "Gambling if they are good people or not..", "The villagers seems nice and get you a place to stay the night.","But for some reason.. they seem kind of suspicious..")
        choice = mutableListOf("Leave at night", "Stay the night")
        nextValue = mutableListOf(107, 111)
        bst.insert(109, util, text, choice, nextValue)

        //Path 107 (kiri)
        util = mutableListOf("a107_dikejar", "running_2")
        text = mutableListOf("The more you think about it, the more suspicious it becomes..", "You decided to leave the village at night.", "Before you go, you bring a rifle full of ammo hidden in the house you\"re at", "It seems the villager noticed you\"re gone and started looking for you", "The nice facade they put up had long gone and they showed their hostility to you.. ")
        choice = mutableListOf("Kill them", "Hide in an abandoned house")
        nextValue = mutableListOf(106, 108)
        bst.insert(107, util, text, choice, nextValue)

        //Path 111 (kanan)
        util = mutableListOf("a41_111_cult", "sad_end")
        text = mutableListOf("You gather your thoughts and decided that it just your negative thinking while eating a dinner they provided", "Unknowingly to you, the villagers had made you ate a brainwashing pill in your dinner soup", "When you wake up in the morning, you forget who you are.", "Turns out, the villagers of that village was a believer of a heretical cult", "And one of them, is you..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(111, util, text, choice, nextValue)

        //Path 106 (kiri)
        util = mutableListOf("a106_crazyman", "sad_end")
        text = mutableListOf("You started to get tired of all of this", "You decided to go back and kill everybody", "After you shot them al with your rifle and burned down their village, you started to regret what you\"re doing", "You don\"t know a way out and started to lose your mind while wandering in the woods alone", "You Died of hunger..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(106, util, text, choice, nextValue)

        //Path 108 (kanan)
        util = mutableListOf("a108", "a108_man_scream")
        text = mutableListOf("The villagers surrounding the house you\"re at right now.", "They started chanting some kind of spell and burned the house down.", "Looks like they wanted to make you a sacrifice", "You tried to escape but they caught you and slain you on the spot", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(108, util, text, choice, nextValue)


        //FULL KIRI!
        //Path 33 (kiri)
        util = mutableListOf("a33", "a33")
        text = mutableListOf("When waiting in car, you saw a faint light from afar", "A man came closer to you and seeing your condition, he suggest you to come with him to his house..")
        choice = mutableListOf("Go with him", "Run away to woods")
        nextValue = mutableListOf(17, 49)
        bst.insert(33, util, text, choice, nextValue)

        //Path 17 (kiri)
        util = mutableListOf("a17_stalker_bmw", "a17_foot_step")
        text = mutableListOf("His house decorated with many hunting equipment", "He also offered you dinner and a place to stay", "He looks like a good guy, but strangely, he sees you with a hungry eye")
        choice = mutableListOf("Spend the night", "Leave at night")
        nextValue = mutableListOf(9, 25)
        bst.insert(17, util, text, choice, nextValue)

        //Path 9 (kiri)
        util = mutableListOf("a9", "a9_eaten")
        text = mutableListOf("You are so tired after eating full course dinner in his house", "You are getting ready to sleep after such a long and tiring day", "After some time, you started to feel a little pain from your hand", "You wake up being chained to the butcher table", "Turns out that man was a canibal and he is ready to butcher you", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(9, util, text, choice, nextValue)

        //Path 25 (kanan)
        util = mutableListOf("a25_run", "u_never_run_2")
        text = mutableListOf("Your guts tells you this man is dangerous and leave the house at night", "And its proved to be true.. That man chased after you with a shotgun in his hand")
        choice = mutableListOf("Hide in a bush", "Jump to the river")
        nextValue = mutableListOf(21, 29)
        bst.insert(25, util, text, choice, nextValue)

        //Path 21 (kiri)
        util = mutableListOf("a21_jungle_bush", "a121_bush")
        text = mutableListOf("You hide in nearby bush", "In your panic, you see a sharp branch")
        choice = mutableListOf("Attack him", "Stay silent")
        nextValue = mutableListOf(19, 23)
        bst.insert(21, util, text, choice, nextValue)

        //Path 19 (kiri)
        util = mutableListOf("a19_shotted_serial", "a114_shotgun")
        text = mutableListOf("You gamble your way out and attack him with the brach you found", "But you missed and he managed to shot you", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(19, util, text, choice, nextValue)

        //Path 23 (kanan)
        util = mutableListOf("a23", "a121_bush")
        text = mutableListOf("You decided this was not the right time to act brave and stay hidden in the bush", "After a long time, the Canibal finally gave up on you.. you sleep in the bush until morning and continue to search for help")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(23, util, text, choice, nextValue)

        //Path 29 (kanan)
        util = mutableListOf("a29_sungai", "a29_jump_to_water")
        text = mutableListOf("You run away from him until you found a river", "Cornered by the Canibal, you jumped to the river and luckily stranded by the side of a city")
        choice = mutableListOf("Tell the cops", "Forget Everything")
        nextValue = mutableListOf(27, 31)
        bst.insert(29, util, text, choice, nextValue)

        //Path 27 (kiri)
        util = mutableListOf("a27_wall", "a27_scream_group")
        text = mutableListOf("You search for the police and told them all that happened", "But the police only laugh and shrugged your story.", "You desperately told them that your story was true, but the police started to doubt your sanity", "You\"re assigned to local mental hospital")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(27, util, text, choice, nextValue)

        //Path 31 (kanan)
        util = mutableListOf("a31_traumatise", "silent")
        text = mutableListOf("After recuperating in the hospital for sometime, you are having a severe trauma about dark woods and forest", "But you decided that life must go on and decided to forget about everything.")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(31, util, text, choice, nextValue)

        //Path 49 (kanan)
        util = mutableListOf("a49", "running_2")
        text = mutableListOf("You are scared and run away deeper to the woods", "Suddenly you stumbled upon a ruin." )
        choice = mutableListOf("Explore", "Enter the ruin")
        nextValue = mutableListOf(41, 57)
        bst.insert(49, util, text, choice, nextValue)

        //Path 41 (kiri)
        util = mutableListOf("a41_111_cult", "sad_end")
        text = mutableListOf("While exploring the ruin, you realized that it looks more like temple than a ruin. All of a sudden, the cult followers surrounded you", "They brought you inside the temple and brainwashed you there until you become a loyal followers just like them.")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(41, util, text, choice, nextValue)

        //Path 57 (kanan)
        util = mutableListOf("a57", "a57_monster_growl")
        text = mutableListOf("You decide to hide in the ruins for some time", "Unfortunately, you triggered the curse that casted on the ruins", "The curse turns you into a monster!", "You hear many footsteps behind you", "Those people was the followers of a hidden cult, and this ruin is their temple.")
        choice = mutableListOf("Talk to them", "Hide and run")
        nextValue = mutableListOf(53, 61)
        bst.insert(57, util, text, choice, nextValue)

        //Path 53 (kiri)
        util = mutableListOf("a53", "a114_shotgun")
        text = mutableListOf("You are hoping those people would know how to break the curse and decided to talk to them.", "But those people get scared of you and hunted you on the spot!", "You Died..")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(53, util, text, choice, nextValue)

        //Path 61 (kanan)
        util = mutableListOf("a61_big_foot", "a61_monster_sound")
        text = mutableListOf("You run away and accepted your fate as a monster")
        choice = mutableListOf(null)
        nextValue = mutableListOf(null)
        bst.insert(61, util, text, choice, nextValue)

        val editor = sp.edit()
        editor.putBoolean("cekTree", true)
        editor.apply()

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()

        //get user data
        sp = getSharedPreferences("dataSP", MODE_PRIVATE)

        initiateTree()
//        println(sp.getBoolean("cekTree", false))
//        if(sp.getBoolean("cekTree", false) == false){
//            initiateTree()
//        }
        val userName = sp.getString("userName", null)

        //Inisialisasi xml
        val btn_Play = findViewById<Button>(R.id.btn_play)
        val btn_Collection = findViewById<Button>(R.id.btn_collection)
        val btn_Score = findViewById<Button>(R.id.btn_score)
        val btn_Exit = findViewById<Button>(R.id.btn_exit)
        val tv_username = findViewById<TextView>(R.id.username)
        val home = findViewById<LinearLayout>(R.id.home)
        val btn_logout = findViewById<Button>(R.id.btn_logout)
//        val svc = Intent(this, BackgroundSoundService::class.java)
//        startService(svc)

        if(userName == null){
            var sendIntent = Intent(this@MainActivity,LoginPage::class.java)
            startActivity(sendIntent)
        }else{
            tv_username.setText("Hello, " + userName)
        }

        tv_username.setOnClickListener{
            val sendIntent = Intent(this@MainActivity, ChoiceHistory::class.java)
            startActivity(sendIntent)
        }

        mediaPlayer= MediaPlayer.create(this, R.raw.dark_forest)
        mediaPlayer?.start() // no need to call prepare(); create() does that for you
//        mediaPlayer?.setVolume(100f, 100f)


        btn_Play.setOnClickListener {
            val eIntent = Intent (this@MainActivity,GameView::class.java)
            eIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(eIntent)
        }

        btn_Score.setOnClickListener {

            val eIntent = Intent (this@MainActivity,HighScore::class.java)
            startActivity(eIntent)

        }
        btn_Collection.setOnClickListener {
            val eIntent = Intent (this@MainActivity,GameCollection::class.java)
            startActivity(eIntent)
        }

        btn_Exit.setOnClickListener {
            mediaPlayer?.release()
            mediaPlayer
            System.exit(-1)

        }

        btn_logout.setOnClickListener {
            val editor = sp.edit()
            editor.clear()
            editor.apply()

            mediaPlayer.stop()
            val sendIntent = Intent(this@MainActivity, this::class.java)
            startActivity(sendIntent)
            finish()
        }


    }
}