package com.bshapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bshapp.R
import com.bshapp.adapter.NotificationRecyclerAdapter
import com.bshapp.model.Notification
import com.bshapp.provider.NotificationDBHelper
import java.util.*


class NotificationActivity : AppCompatActivity() {

    private lateinit var mlist: ArrayList<Notification>
    lateinit var mAdapter: NotificationRecyclerAdapter
    private lateinit var mRecyclerView:RecyclerView
    private lateinit var db:NotificationDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        mlist=ArrayList<Notification>()
        mAdapter=NotificationRecyclerAdapter(mlist)
        getSetData()
        //recyclerview setup
        mRecyclerView=findViewById(R.id.notification_recyclerview)
        val mLayoutManager= LinearLayoutManager(this)
        mRecyclerView.layoutManager=mLayoutManager
        //mAdapter=NotificationRecyclerAdapter(mlist)
        mRecyclerView.adapter=mAdapter
    }

    private fun getSetData(){
        //get data from database and set it to the recycler view
        var db = NotificationDBHelper(this)
        db.Store("database test", "test",  "false")
        mlist.addAll(db.readNotifications())
        mAdapter.setData(mlist)
        //for data base testing
        /*
        mlist.add(Notification(4, "no problem", "messaghigjoidsjgoijdsiogjose"))
        mlist.add(Notification(2, "title  ok yes", "messgjisdjgoisjghsage"))
        mlist.add(Notification(1, "helllooooo", "messagosjgiodjoijgde"))
        //getting the list from database
        db =  NotificationDBHelper(this)
        val list: ArrayList<Notification> =ArrayList<Notification>()
        list.addAll(db.readNotifications())
        */
    }
}