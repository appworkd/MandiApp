package com.appwork.app

import android.app.Application
import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by Vivek Kumar belongs to APP WORK  on 16-11-2020.
 */
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        JodaTimeAndroid.init(this)
    }
}