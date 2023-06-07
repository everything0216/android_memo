package com.example.final_project

import android.app.Application

class GlobalVariable: Application() {
    companion object {
        //存放變數
        val remindersList: ArrayList<Reminder> = ArrayList()
        //修改 變數値

    }
}