package com.ara.lesson_3.utils

import android.content.Context
import android.content.SharedPreferences

//я выбрал object вместо класса потому, что так удобнее и не надо создавать экземпляр класса
object PreferenceHelper {
    private lateinit var sharedPreferences: SharedPreferences
    private val ONBOARDING_SHOWN = "onboard_show"
    private val IS_LINEAR = "is_linear"

    fun unit(context: Context) {
        sharedPreferences = context.getSharedPreferences("shared", Context.MODE_PRIVATE)
    }

    fun isOnboardShow(): Boolean {
        return sharedPreferences.getBoolean(ONBOARDING_SHOWN, false)
    }

    fun setOnboardShow(value: Boolean) {
        sharedPreferences.edit().putBoolean(ONBOARDING_SHOWN, value).apply()
    }


    fun isLinear(): Boolean {
        return sharedPreferences.getBoolean(IS_LINEAR, true)
    }

    fun setLinear(value: Boolean) {
        sharedPreferences.edit().putBoolean(IS_LINEAR, value).apply()
    }
}