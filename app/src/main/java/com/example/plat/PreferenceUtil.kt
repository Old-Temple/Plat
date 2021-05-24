package com.example.plat

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences

class PreferenceUtil(context: Context){
    private var prefs: SharedPreferences = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getValue(key:String, defValue:String):String{
        return prefs.getString(key, defValue).toString()
    }

    fun setValue(key: String, Value:String){
        prefs.edit().putString(key, Value).apply()
    }

    fun deleteValue(){
        prefs.edit().clear().apply()
    }
}