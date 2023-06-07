package com.example.final_project

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class ReminderReceiver() {
     fun onReceive(context: Context, intent: Intent) {
        val reminderText = intent.getStringExtra("reminderText")
        Toast.makeText(context, "Reminder: $reminderText", Toast.LENGTH_SHORT).show()
    }
}