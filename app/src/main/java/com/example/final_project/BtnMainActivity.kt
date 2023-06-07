package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BtnMainActivity : AppCompatActivity() {
    lateinit var displayBtn : Button
    lateinit var setRemindBtn : Button
    lateinit var setMemo : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose)

        displayBtn = findViewById(R.id.displayRemind_btn)
        setRemindBtn = findViewById(R.id.setRemind_btn)
        setMemo = findViewById(R.id.setMemo_btn)


        setRemindBtn.setOnClickListener{
            val intent = Intent(this, RemindActivity::class.java)
            startActivity(intent)
        }

        setMemo.setOnClickListener{
            val intent = Intent(this, MemoMainActivity::class.java)
            startActivity(intent)
        }

        displayBtn.setOnClickListener{
            val intent = Intent(this, ShowLIst::class.java)
            startActivity(intent)
        }


    }
}