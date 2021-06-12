package com.bshapp.provider

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.bshapp.model.Notification
import com.bshapp.provider.NotificationContract.NotificationEntry.Companion.ID
import com.bshapp.provider.NotificationContract.NotificationEntry.Companion.MESSAGE
import com.bshapp.provider.NotificationContract.NotificationEntry.Companion.TABLE_NAME
import com.bshapp.provider.NotificationContract.NotificationEntry.Companion.TITLE
import java.util.*

class NotificationDBHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_NOTIFICATION_TABLE   :String="CREATE TABLE " + TABLE_NAME +" ( "+ ID +" INT NOT NULL, "+ TITLE +" INT NOT NULL, "+ MESSAGE +" INT NOT NULL);"
        db!!.execSQL(CREATE_NOTIFICATION_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "Notifications"
    }
    //, image: Bitmap?
    fun Store(messageBody: String?, title: String , TrueOrFalse: String?) {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(ID, 1)
        values.put(TITLE, title)
        values.put(MESSAGE, messageBody)
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

     fun readNotifications(): ArrayList<Notification> {
        // on below line we are creating a
        // database for reading our database.
        val db: SQLiteDatabase = this.readableDatabase

        // on below line we are creating a cursor with query to read data from database.
        val cursorNotification: Cursor = db.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )

        // on below line we are creating a new array list.
        val NotificationModelArrayList: ArrayList<Notification> = ArrayList()

        // moving our cursor to first position.
        if (cursorNotification.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                NotificationModelArrayList.add(
                    Notification(
                        cursorNotification.getInt(cursorNotification.getColumnIndexOrThrow(ID)),
                        cursorNotification.getString(cursorNotification.getColumnIndexOrThrow(TITLE)),
                        cursorNotification.getString(cursorNotification.getColumnIndexOrThrow(
                            MESSAGE
                        ))
                    )
                )
            } while (cursorNotification.moveToNext())
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorNotification.close()
        return NotificationModelArrayList

    }


}