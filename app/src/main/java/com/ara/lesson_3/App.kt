package com.ara.lesson_3

import android.app.Application
import com.ara.lesson_3.utils.PreferenceHelper

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.unit(this)
    }

}