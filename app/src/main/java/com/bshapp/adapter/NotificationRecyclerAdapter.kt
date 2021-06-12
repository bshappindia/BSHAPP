package com.bshapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bshapp.R
import com.bshapp.model.Notification


class NotificationRecyclerAdapter(private var notificationList: List<Notification>):
    RecyclerView.Adapter<NotificationRecyclerAdapter.NotificationViewHolder>() {

    fun setData(list:List<Notification>) {
        this.notificationList=list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.notification_item, parent, false)

        return NotificationViewHolder(view)
    }



    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.mTitle.text= notificationList[position].title
        holder.mMessage.text= notificationList[position].message

    }

    override fun getItemCount(): Int {
        return notificationList.size
    }

    class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mTitle: TextView = itemView.findViewById(R.id.notification_title)
        val mMessage: TextView= itemView.findViewById(R.id.notification_details)
        }


}