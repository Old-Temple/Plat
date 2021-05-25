package com.example.plat

import android.app.Application
import android.content.Context
import android.content.SharedPreferences


//프리퍼랜스 저장용
class PlatPrefs : Application() {
    companion object{
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}

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

