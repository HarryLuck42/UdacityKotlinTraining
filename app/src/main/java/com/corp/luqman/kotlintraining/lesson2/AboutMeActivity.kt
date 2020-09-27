package com.corp.luqman.kotlintraining.lesson2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.corp.luqman.kotlintraining.lesson2.model.MyName
import com.corp.luqman.kotlintraining.R
import com.corp.luqman.kotlintraining.databinding.AboutmeActivityBinding

class AboutMeActivity : AppCompatActivity() {

    private lateinit var binding : AboutmeActivityBinding

    private val myName : MyName =
        MyName("Hariyanto Lukman")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.aboutme_activity
        )
        binding.myName = myName
        binding.buttonDone.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname (view : View){
//        val editText = findViewById<EditText>(R.id.nickname_edit)
//        val nicknameTv = findViewById<TextView>(R.id.nickname_text)

        binding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
            nicknameEdit.visibility = View.GONE
            buttonDone.visibility = View.GONE
            nicknameText.visibility = View.VISIBLE
        }
//        nicknameTv.text = editText.text
//        editText.visibility = View.GONE
//        view.visibility = View.GONE
//        nicknameTv.visibility = View.VISIBLE

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}