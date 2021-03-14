package com.omurgun.learncorebackendandroidui.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class CustomSharedPreferences {

    companion object {

        private const val PREFERENCES_TIME = "preferences_time"
        internal var sharedPreferences: SharedPreferences? = null

        @Volatile private var instance: CustomSharedPreferences? = null
        private val lock = Any()

        operator fun invoke(context: Context) : CustomSharedPreferences = instance ?: synchronized(lock) {
            instance ?: makeCustomSharedPreferences(context).also {
                instance = it
            }
        }

        private fun makeCustomSharedPreferences(context: Context) : CustomSharedPreferences {
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            println("girdi")
            return CustomSharedPreferences()
        }

    }




    /*fun saveToLocalUser(internalUser: InternalUser){
        sharedPreferences?.edit(commit = true){
            putString(PROJECT_USER_NAME,internalUser.name)
            putString(PROJECT_USER_EMAIL,internalUser.email)
            putString(PROJECT_USER_PASSWORD,internalUser.password)
            putString(PROJECT_USER_ACCESS_TOKEN,internalUser.accessToken)
            putString(PROJECT_USER_DEVICE_TOKEN,internalUser.deviceToken)
            putString(PROJECT_USER_DEVICE_TYPE,internalUser.deviceType)
        }
    }


    fun getToLocalUser() : InternalUser{
        val username = sharedPreferences?.getString(PROJECT_USER_NAME,"")
        val email = sharedPreferences?.getString(PROJECT_USER_EMAIL,"")
        val password = sharedPreferences?.getString(PROJECT_USER_PASSWORD,"")
        val accessToken = sharedPreferences?.getString(PROJECT_USER_ACCESS_TOKEN,"")
        val deviceToken = sharedPreferences?.getString(PROJECT_USER_DEVICE_TOKEN,"")
        val deviceType = sharedPreferences?.getString(PROJECT_USER_DEVICE_TYPE,"")

        return InternalUser(username!!,email!!,password!!,accessToken!!,deviceToken!!,deviceType!!)
    }


    fun removeToLocalUser(){
        sharedPreferences?.edit(commit = true){
            putString(PROJECT_USER_NAME,"")
            putString(PROJECT_USER_EMAIL,"")
            putString(PROJECT_USER_PASSWORD,"")
            putString(PROJECT_USER_ACCESS_TOKEN,"")
        }
    }



    fun getToAccessToken() : String{

        return sharedPreferences?.getString(PROJECT_USER_ACCESS_TOKEN,"")!!
    }

    fun saveToAccessToken(accessToken: String){
        sharedPreferences?.edit(commit = true){
            putString(PROJECT_USER_ACCESS_TOKEN,accessToken)
        }
    }

    fun getToDeviceToken() : String{
        return sharedPreferences?.getString(PROJECT_USER_DEVICE_TOKEN,"")!!
    }

    fun saveToDeviceToken(deviceToken: String){
        sharedPreferences?.edit(commit = true){
            putString(PROJECT_USER_DEVICE_TOKEN,deviceToken)
        }
    }















    fun saveTime(time: Long) {
        sharedPreferences?.edit(commit = true){
            putLong(PREFERENCES_TIME,time)
        }
    }

    fun getTime() = sharedPreferences?.getLong(PREFERENCES_TIME,0)*/



}