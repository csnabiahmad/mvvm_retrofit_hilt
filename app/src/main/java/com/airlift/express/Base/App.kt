package com.airlift.express.Base

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)


    }

    override fun onCreate() {
        super.onCreate()

        ctx = this
    }

    companion object {
        val TAG: String = App::class.java
            .simpleName
        lateinit var ctx: App

        fun getAppContext(): App {
            return ctx
        }

    }



}