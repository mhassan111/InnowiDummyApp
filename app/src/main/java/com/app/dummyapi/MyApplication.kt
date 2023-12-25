package com.app.dummyapi

import android.content.Context
import androidx.multidex.MultiDexApplication

class MyApplication : MultiDexApplication() {

    companion object {
        var myApplication: MyApplication? = null
            private set

        @JvmStatic
        val context: Context
            get() = myApplication!!.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        myApplication = this
    }

}