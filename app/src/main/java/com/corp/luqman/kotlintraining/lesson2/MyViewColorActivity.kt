package com.corp.luqman.kotlintraining.lesson2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.corp.luqman.kotlintraining.R
import kotlinx.android.synthetic.main.activity_my_view_color.*

class MyViewColorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_view_color)
        setListeners()
    }

    private fun setListeners(){
        val listBox : List<View> = listOf(text_box_one, text_box_two,
        text_box_three, text_box_four, text_box_five, layout_box_color,
        button_red, button_green, button_yellow)

        for(box in listBox){
            box.setOnClickListener { makeColour(box) }
        }
    }

    private fun makeColour(box: View) {
        when(box.id){
            R.id.text_box_one -> box.setBackgroundColor(Color.DKGRAY)
            R.id.text_box_two -> box.setBackgroundColor(Color.GRAY)

            R.id.text_box_three -> box.setBackgroundResource(android.R.color.holo_green_light)
            R.id.text_box_four -> box.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.text_box_five -> box.setBackgroundResource(android.R.color.holo_green_light)

            R.id.button_red -> text_box_three.setBackgroundResource(
                R.color.my_red
            )
            R.id.button_yellow -> text_box_four.setBackgroundResource(
                R.color.my_yellow
            )
            R.id.button_green -> text_box_five.setBackgroundResource(
                R.color.my_green
            )

            else -> box.setBackgroundColor(Color.LTGRAY)
        }
    }
}