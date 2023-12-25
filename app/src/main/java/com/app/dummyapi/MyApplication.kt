package com.app.dummyapi

import android.app.Application
import android.content.Context

class MyApplication : Application() {

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