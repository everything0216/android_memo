package com.example.final_project

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import com.example.final_project.RemindActivity

class MyAdapter(private val context: Context, private val dataList: ArrayList<Reminder>) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_example, parent, false)
        return ViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        val instant = Instant.ofEpochMilli(item.time)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        val formattedTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(localDateTime)

        holder.textView.text = item.title
        holder.note.text = item.note
        holder.time.text = formattedTime

        println(item.note)
        println(formattedTime)
        println(item.title)
        println("---------------------------------")
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun getReminder(position: Int): Reminder? {
        return dataList[position]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView = itemView.findViewById(R.id.textView)
        val time: TextView = itemView.findViewById(R.id.time)
        val note: TextView = itemView.findViewById(R.id.note)
        val deleteBtn: Button = itemView.findViewById(R.id.dl_btn)

        init {
            deleteBtn.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {

                    val intent = Intent(context, Notification::class.java)
                    intent.putExtra("cancel", true);
                    val pendingIntent = PendingIntent.getBroadcast(
                        context,
                        dataList[position].notificationId,
                        intent,
                        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                    )
                    //pendingIntent.cancel();
                    println("id=="+dataList[position].notificationId)
                    dataList.removeAt(position)
                    notifyItemRemoved(position)

                }
            }
        }
    }
}