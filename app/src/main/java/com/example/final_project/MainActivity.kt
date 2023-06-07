package com.example.final_project
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var home_btn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        home_btn = findViewById(R.id.home_btn)
        home_btn .setOnClickListener {
            // 跳轉到目標 Activity
            val intent = Intent(this, BtnMainActivity::class.java)
            startActivity(intent)
        }
    }

}