package com.android.example.private_network_class

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val INFO_APP = "app"

    private val infoAppPreferences: SharedPreferences = context.getSharedPreferences(INFO_APP,
        Context.MODE_PRIVATE
    )

    var prefString: String?
    get() = infoAppPreferences.getString(INFO_APP, "")
    set(value) = infoAppPreferences.edit().putString(INFO_APP, value).apply()
}