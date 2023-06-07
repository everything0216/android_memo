package com.example.final_project

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.final_project.GlobalVariable.Companion.remindersList
import com.example.final_project.databinding.ActivityMainBinding
import java.util.*


class RemindActivity : AppCompatActivity(){


        lateinit var set: Button
        lateinit var reset: Button
        lateinit var back: Button
        lateinit var name: EditText
        lateinit var note: EditText
        lateinit var binding: ActivityMainBinding
        lateinit var timePicker: TimePicker
        lateinit var datePicker: DatePicker
        val notiId : Int = 0



        @RequiresApi(Build.VERSION_CODES.O)
        override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.set_remind)

                name = findViewById(R.id.name)
                note = findViewById(R.id.note)
                set = findViewById(R.id.set)
                datePicker = findViewById(R.id.datePicker)
                timePicker = findViewById(R.id.timePicker)
                reset = findViewById(R.id.set)
                back = findViewById(R.id.set_remind_back)

                binding = ActivityMainBinding.inflate(layoutInflater)
                //setContentView(binding.root)
                createNotificationChannel()

                set.setOnClickListener{
                        scheduleNotification()
                }

                back.setOnClickListener {
                        finish()
                }


        }

        //set notification
        private fun scheduleNotification() {
                val intent = Intent(applicationContext, Notification::class.java)
                val title = name.text.toString()
                val message = note.text.toString()
                intent.putExtra(titleExtra,title)
                intent.putExtra(messageExtra,message)
                intent.putExtra("nowId", notificationID)
                val reminder = Reminder()

                val pendingIntent = PendingIntent.getBroadcast(
                        applicationContext,
                        notificationID,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )

                val  alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                val time = getTime()
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent)

                println(notificationID)
                val remind = Reminder()
                val t:String = title
                val n:String = message
                val tm:Long = time
                val noti:Int = notificationID
                remind.note = n
                remind.title = t
                remind.time = tm
                remind.notificationId = noti
                remindersList.add(remind)
                println(remind)
                for (i in remindersList) {
                        println(i.note)
                }
                notificationID++
                showAlert(time, title, message)
        }


        companion object {
                fun deleteScheduleNotification(context: Context?, notificationId: Int) {

                        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        notificationManager.cancel(notificationId)
                        println("Cancel notification with id: $notificationId")
                }
        }



        private fun showAlert(time: Long, title: String, message: String) {

                val date = Date(time)
                val dataFormat = android.text.format.DateFormat.getDateFormat(applicationContext)
                val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

                AlertDialog.Builder(this)
                        .setTitle("Notification Scheduled")
                        .setMessage(
                                "Title " + title +
                                "\nMessage " + message +
                                "\nAt: " + dataFormat.format(date) + " " + timeFormat.format(date))
                        .setPositiveButton("ok"){_,_ ->}
                        .show()
        }

        private fun getTime(): Long {

                val min = timePicker.minute
                val hour = timePicker.hour
                val day = datePicker.dayOfMonth
                val month = datePicker.month
                val year = datePicker.year

                val calendar = Calendar.getInstance()
                calendar.set(year, month, day, hour, min)
                return calendar.timeInMillis

        }

        @RequiresApi(Build.VERSION_CODES.O)
        private fun createNotificationChannel() {
                val name = "notification channel"
                val desc = "A description of the channel"
                val importance = NotificationManager.IMPORTANCE_DEFAULT
                val channel = NotificationChannel(channelID, name, importance)
                channel.description = desc
                val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(channel)
        }


}

