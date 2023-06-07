package com.example.final_project

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.*
import com.example.final_project.GlobalVariable.Companion.remindersList

var notificationID = 1000
var nowNotificationID = 1000
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Notification:BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {

        val notificationId = intent.getIntExtra("nowId",0)
        nowNotificationID = notificationId

        var isCancel = intent.getBooleanExtra("cancel", false);

        println("Called")
        if (isCancel) {
            println("Canceled")
            return;
        }

        val notification = NotificationCompat.Builder(context,channelID)
            .setSmallIcon(R.drawable.dog)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(nowNotificationID,notification)

        for (item in remindersList) {
            if(item.notificationId==nowNotificationID){
                remindersList.remove(item)
                return
            }
        }
    }

}