package com.example.final_project

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.final_project.GlobalVariable.Companion.remindersList


class ShowLIst : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var back_btn: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        back_btn = findViewById(R.id.back_btn)

        back_btn.setOnClickListener{
            val intent = Intent(this, BtnMainActivity::class.java)
            startActivity(intent)
        }

        recyclerView = findViewById(R.id.show_lv)
        val delete_btn=recyclerView.findViewById<Button>(R.id.dl_btn)
/*
        delete_btn.setOnClickListener{
            getReminder(position)
        }
*/
        val adapter = MyAdapter(this, remindersList)
        recyclerView.adapter = adapter


        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }
}