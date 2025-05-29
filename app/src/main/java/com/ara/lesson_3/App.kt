package com.ara.lesson_3

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ara.lesson_3.data.db.AppDataBase
import com.ara.lesson_3.utils.PreferenceHelper

class App: Application() {

    companion object{
        var appDatabase: AppDataBase? = null
    }

    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.unit(this)

        getInstance()
    }

    private fun getInstance(): AppDataBase? {
        if (appDatabase == null) {
            appDatabase = applicationContext?.let { context ->
                Room.databaseBuilder(context,
                                AppDataBase::class.java,
                                "note_database").fallbackToDestructiveMigration(false)
                    .allowMainThreadQueries().build()
            }
        }
        return appDatabase
    }

}