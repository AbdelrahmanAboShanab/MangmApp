package com.brightskiesinc.mangm.data.source.local

import android.content.Context
import javax.inject.Inject

class SharedPrefStorage @Inject constructor(context: Context) {

    private val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    interface StorageKey {
        fun rawValue(): String = toString()
    }

    enum class Key constructor(val value: String) : StorageKey {

        FIRST_TIME_LAUNCH("FIRST_TIME_LAUNCH");

        override fun toString(): String {
            return value
        }
    }

    fun getBoolean(key: StorageKey, defValue: Boolean): Boolean {
        return sharedPref.getBoolean(key.rawValue(), defValue)
    }

    fun putBoolean(key: StorageKey, value: Boolean) {
        sharedPref.edit().putBoolean(key.rawValue(), value).apply()
    }

    companion object {
        const val PREF_NAME = "MangmSharedPref"
    }
}