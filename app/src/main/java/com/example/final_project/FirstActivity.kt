package com.example.final_project
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class FirstActivity : Activity() {
    lateinit var nmSet: EditText
    lateinit var btnSet: Button

    //companion object {
    //    val NICKNAME: String = "NICKNAME"
    //}

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.set_nickname)

        btnSet = findViewById(R.id.idBtnSetNickname)
        btnSet.setOnClickListener{ getNickname() }

        nmSet = findViewById(R.id.idSetNickname)

    }
    private fun getNickname(){
        //var inputNM: String = nmSet.text.toString()
        val intent = Intent()
        intent.setClass(this@FirstActivity, MainActivity::class.java)
        //intent.putExtra(NICKNAME, inputNM)
        startActivity(intent)
    }
}