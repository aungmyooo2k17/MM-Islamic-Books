package org.m2cs.mmislamicbooks.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.text.TextUtils
import android.util.Log
import com.google.gson.Gson
import org.m2cs.mmislamicbooks.App
import org.m2cs.mmislamicbooks.App.Companion.downIds
import com.google.gson.reflect.TypeToken
import org.m2cs.mmislamicbooks.App.Companion.TAG
import org.m2cs.mmislamicbooks.data.vo.BookVO


object PreferencesUtils {
    var PREFERENCE_NAME = "MMISLAMICBOOKS"

    fun putString(context: Context, key: String, value: String): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString(key, value)
        return editor.commit()
    }


    fun getString(context: Context, key: String): String {
        return getString(context, key, null)
    }

    fun removeValue(context: Context, key: String) {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        settings.edit().remove(key).apply()
    }


    fun getString(context: Context, key: String, defaultValue: String?): String {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getString(key, defaultValue)
    }

    fun putInt(context: Context, key: String, value: Int): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt(key, value)
        return editor.commit()
    }


    fun getInt(context: Context, key: String): Int {
        return getInt(context, key, -1)
    }


    fun getInt(context: Context, key: String, defaultValue: Int): Int {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getInt(key, defaultValue)
    }


    fun putLong(context: Context, key: String, value: Long): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putLong(key, value)
        return editor.commit()
    }

    fun getLong(context: Context, key: String): Long {
        return getLong(context, key, -1)
    }

    fun getLong(context: Context, key: String, defaultValue: Long): Long {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getLong(key, defaultValue)
    }


    fun putFloat(context: Context, key: String, value: Float): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putFloat(key, value)
        return editor.commit()
    }


    fun getFloat(context: Context, key: String): Float {
        return getFloat(context, key, -1f)
    }

    /**
     * get float preferences
     *
     * @param context
     * @param key          The name of the preference to retrieve
     * @param defaultValue Value to return if this preference does not exist
     * @return The preference value if it exists, or defValue. Throws ClassCastException if there is a preference with
     * this name that is not a float
     */
    fun getFloat(context: Context, key: String, defaultValue: Float): Float {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getFloat(key, defaultValue)
    }

    fun putBoolean(context: Context, key: String, value: Boolean): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(key, value)
        return editor.commit()
    }


    fun getBoolean(context: Context, key: String): Boolean {
        return getBoolean(context, key, false)
    }


    fun getBoolean(context: Context, key: String, defaultValue: Boolean): Boolean {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        return settings.getBoolean(key, defaultValue)
    }

    fun putArrayList(context: Context) {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        var gson = Gson()
        var jsonList = gson.toJson(App.downIds)
        editor.putString("downIds", jsonList)
        editor.commit()

    }


    fun getArrayList(context: Context, key: String): ArrayList<Long> {
        var gson = Gson()
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        var json = settings.getString(key, "")
        val type = object : TypeToken<ArrayList<Long>>() {}.type
        return gson.fromJson(json, type)
    }


    fun putBookVo(context: Context, key: String, bokVO: BookVO) {
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        val editor = settings.edit()
        var gson = Gson()
        var jsonBookVo = gson.toJson(bokVO)
        editor.putString(key, jsonBookVo)
        editor.commit()

    }
    fun getBookVo(context: Context,key:String):BookVO
    {
        var gson = Gson()
        val settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
        var json = settings.getString(key, "")
        val type = object : TypeToken<BookVO>() {}.type
        return gson.fromJson(json, type)
    }

}