package com.example.klikdokter.domain.section

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException


class SharedHelper(context: Context) {

    private val sharedPreferences: SharedPreferences
    private var editor: SharedPreferences.Editor? = null
    private val gson: Gson by lazy { Gson() }

    init {
        sharedPreferences = getSharedPreference(context)
        editor = sharedPreferences.edit()
    }

    fun getSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    fun <T> put(key: String, value: T) {
        when (value) {
            is Boolean -> editor?.putBoolean(key, value)
            is String -> editor?.putString(key, value)
            is Int -> editor?.putInt(key, value)
            is Float -> editor?.putFloat(key, value)
            is Long -> editor?.putLong(key, value)
            else -> throw Error("Fatal Error, unknown type value trying to be saved in SharedPreff value: $value")
        }

        editor?.commit()
    }

    fun <T> valueFrom(key: String, defaultValue: T): T? {

        return when (defaultValue) {
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            else -> throw Error("Fatal Error, unknown type value ")
        }
    }

    fun <T> putList(key: String, list: List<T>) {
        val json = gson.toJson(list)
        putString(key, json)
    }

    fun <T> putObj(key: String, obj: T) {
        val json = gson.toJson(obj)
        putString(key, json)
    }

    fun <T> getObj(key: String, type: Class<T>): T? = valueFrom(key, "")?.let {
        try {
            gson.fromJson(it, type) as T
        } catch (jse: JsonSyntaxException) {
            null
        }
    }

    private fun putString(key: String, isi: String) = sharedPreferences.edit().putString(key, isi).apply()

    fun remove(key: String) {
        editor?.remove(key)?.commit()
    }

    companion object {
        const val token = "token"
    }
}
