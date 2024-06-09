package com.handheld.exp.utils

import android.content.Context

class PreferenceUtils(private val context: Context) {

    fun setPreference(key: String, value: String){
        val sharedPref = context.getSharedPreferences(
            "PREFERENCES",
            Context.MODE_PRIVATE)

        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getPreference(key: String): String?{
        val sharedPref = context.getSharedPreferences(
            "PREFERENCES",
            Context.MODE_PRIVATE)

        return sharedPref.getString(key, null)
    }

}