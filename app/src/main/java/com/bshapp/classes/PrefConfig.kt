package com.bshapp.classes

import android.content.Context
import android.content.SharedPreferences




/**
 * Created By SPS on 12-06-2021.
 */
object PrefConfig {
    private const val spName = "com.bshapp"
    private const val PREF_TOTAL_KEY = "stateParam"
    fun saveSelectedState(context: Context, total: String) {
        val pref: SharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(PREF_TOTAL_KEY, total)
        editor.apply()
    }

    fun loadSelectedState(context: Context): String? {
        val pref: SharedPreferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
        return pref.getString(PREF_TOTAL_KEY, null)
    }
}