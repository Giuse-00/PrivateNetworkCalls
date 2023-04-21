package com.android.example.private_network_class.SharedPreferences

import android.app.Application
import androidx.room.Room
import com.android.example.private_network_class.AppDatabase

val prefs: Prefs by lazy {
    MyApplication.prefs!!
}

class MyApplication: Application(){

    val database by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "my_db"
        ).build()
    }

    companion object{
        var prefs: Prefs? = null
        lateinit var instance: MyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
        prefs = Prefs(applicationContext)

    }
}