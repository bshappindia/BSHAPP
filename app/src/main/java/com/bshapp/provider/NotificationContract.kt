package com.bshapp.provider

import android.provider.BaseColumns


object NotificationContract {

    /* Inner class that defines the table contents */
    class NotificationEntry : BaseColumns {
        companion object {
            val TABLE_NAME = "Notifications"
            val ID = "id"
            val TITLE = "title"
            val MESSAGE = "message"
        }
    }
}