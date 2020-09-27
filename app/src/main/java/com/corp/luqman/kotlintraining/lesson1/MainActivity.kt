package com.corp.luqman.kotlintraining.lesson1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.corp.luqman.kotlintraining.R
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var buttonRoll : Button = findViewById(R.id.roll_button)
//        buttonRoll.text = "Let's Roll"
        buttonRoll.setOnClickListener {
//            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            RollDice()
        }
        diceImage = findViewById(R.id.image_dice)
    }
    //Fungsi untuk mengacak nilai dadu sesuai gambar yang kita masukkan
    private fun RollDice() {
//        val resultText : TextView = findViewById(R.id.result_text)

        val randomNumber = Random().nextInt(6) + 1
        val imageDiceResource = when (randomNumber){
            1-> R.drawable.dice_1
            2-> R.drawable.dice_2
            3-> R.drawable.dice_3
            4-> R.drawable.dice_4
            5-> R.drawable.dice_5
            6-> R.drawable.dice_6
            else -> R.drawable.empty_dice
        }

        diceImage.setImageResource(imageDiceResource)


//        resultText.text = randomNumber.toString()
    }
}
