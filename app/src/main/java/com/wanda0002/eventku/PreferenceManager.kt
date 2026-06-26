package com.wanda0002.eventku

import android.content.Context

class PreferenceManager(context: Context) {

    private val pref = context.getSharedPreferences("eventku_pref", Context.MODE_PRIVATE)

    fun saveUser(name: String) {
        pref.edit().putString("user", name).apply()
    }

    fun getUser(): String? {
        return pref.getString("user", null)
    }
}